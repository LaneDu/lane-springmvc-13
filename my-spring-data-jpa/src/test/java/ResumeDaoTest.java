import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @author lane
 * @date 2021年04月09日 上午10:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ResumeDaoTest {
    //注入测试的ioc对象
    @Autowired
    private ResumeDao resumeDao;


    /**
     * dao层接口调用，分成两块：
     * 1、基础的增删改查
     * 2、专门针对查询的详细分析使用
     */


    /*
     * Hibernate: select resume0_.id as id1_0_0_, resume0_.address as address2_0_0_, resume0_.name as name3_0_0_, resume0_.phone as phone4_0_0_ from tb_resume resume0_ where resume0_.id=?
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * @author lane
     * @date 2021/4/9 上午10:47
     */
    @Test
    public void testFindById(){
        Optional<Resume> optional = resumeDao.findById(1l);
        Resume resume = optional.get();
        System.out.println(resume);


    }
    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * Resume{id=2, name='李四', address='上海', phone='151000000'}
     * Resume{id=3, name='王五', address='⼴州', phone='153000000'}
     * @author lane
     * @date 2021/4/9 上午11:59
     */
    @Test
    public void findAll(){
        List<Resume> all = resumeDao.findAll();
        for (int i = 0; i < all.size(); i++) {
            Resume resume =  all.get(i);
            System.out.println(resume);

        }
    }
    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=? and resume0_.id=1
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * @author lane
     * @date 2021/4/9 上午11:59
     */
    @Test
    public void findByExample(){
        Resume resume = new Resume();
        resume.setId(1l);
        resume.setName("张三");
        Example<Resume> example = Example.of(resume);
        List<Resume> all =  resumeDao.findAll(example);
        for (int i = 0; i < all.size(); i++) {
            Resume resume2 =  all.get(i);
            System.out.println(resume2);

        }
    }
    /**
     *
     * Hibernate: insert into tb_resume (address, name, phone) values (?, ?, ?)
     * Resume{id=4, name='lane', address='崇明', phone='157'}
     * @author lane
     * @date 2021/4/9 上午11:59
     */
    @Test
    public void testInsert(){

        Resume resume = new Resume();
        resume.setName("lane");
        resume.setAddress("崇明");
        resume.setPhone("157");
        //新增和更新都使用save方法，通过传入的对象的主键有无来区分，没有主键信息那就是新增，有主键信息就是更新
        Resume save = resumeDao.save(resume);
        System.out.println(save);

    }
    /**
     * Hibernate: select resume0_.id as id1_0_0_, resume0_.address as address2_0_0_, resume0_.name as name3_0_0_, resume0_.phone as phone4_0_0_ from tb_resume resume0_ where resume0_.id=?
     * Hibernate: update tb_resume set address=?, name=?, phone=? where id=?
     * Resume{id=4, name='lane1', address='崇明1', phone='1573'}
     * @author lane
     * @date 2021/4/9 下午12:00 
     */
    @Test
    public void testUpdate(){

        Resume resume = new Resume();
        resume.setName("lane1");
        resume.setAddress("崇明1");
        resume.setPhone("1573");
        resume.setId(4l);
        Resume save = resumeDao.save(resume);
        System.out.println(resume);
    }
    /**
     * Hibernate: select resume0_.id as id1_0_0_, resume0_.address as address2_0_0_, resume0_.name as name3_0_0_, resume0_.phone as phone4_0_0_ from tb_resume resume0_ where resume0_.id=?
     * Hibernate: delete from tb_resume where id=?
     * @author lane
     * @date 2021/4/9 下午12:01
     */
    @Test
    public void testDelete(){

        Resume resume = new Resume();
        resume.setName("lane1");
        resume.setAddress("崇明1");
        resume.setPhone("1573");
        resume.setId(4l);
        resumeDao.delete(resume);

    }


    /**
     * ========================针对查询的使用进行分析=======================
     * 方式一：调用继承的接口中的方法  findOne(),findById()
     * 方式二：可以引入jpql（jpa查询语言）语句进行查询 (=====>>>> jpql 语句类似于sql，只不过sql操作的是数据表和字段，jpql操作的是对象和属性，比如 from Resume where id=xx)  hql
     * 方式三：可以引入原生的sql语句
     * 方式四：可以在接口中自定义方法，而且不必引入jpql或者sql语句，这种方式叫做方法命名规则查询，也就是说定义的接口方法名是按照一定规则形成的，那么框架就能够理解我们的意图
     * 方式五：动态查询
     *       service层传入dao层的条件不确定，把service拿到条件封装成一个对象传递给Dao层，这个对象就叫做Specification（对条件的一个封装）
     *
     *
     *          // 根据条件查询单个对象
     *          Optional<T> findOne(@Nullable Specification<T> var1);
     *          // 根据条件查询所有
     *          List<T> findAll(@Nullable Specification<T> var1);
     *          // 根据条件查询并进行分页
     *          Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);
     *          // 根据条件查询并进行排序
     *          List<T> findAll(@Nullable Specification<T> var1, Sort var2);
     *          // 根据条件统计
     *          long count(@Nullable Specification<T> var1);
     *
     *      interface Specification<T>
     *              toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);用来封装查询条件的
     *                  Root:根属性（查询所需要的任何属性都可以从根对象中获取）
     *                  CriteriaQuery 自定义查询方式 用不上
     *                  CriteriaBuilder 查询构造器，封装了很多的查询条件（like = 等）
     *
     *
     */

    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.id=? and resume0_.name=?
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * @author lane
     * @date 2021/4/9 下午12:16
     */
    @Test
    public void findByJqpl(){
        List<Resume> list = resumeDao.findByJpql(1l, "张三");
        for (int i = 0; i < list.size(); i++) {
            Resume resume =  list.get(i);
            System.out.println(resume);
        }

    }
    /**
     * Hibernate: select * from tb_resume where name like ? and address like ?
     * Resume{id=2, name='李四', address='上海', phone='151000000'}
     * @author lane
     * @date 2021/4/9 下午12:36
     */
    @Test
    public void findBySql(){
        List<Resume> list = resumeDao.findBySql("李%", "%海");
        for (int i = 0; i < list.size(); i++) {
            Resume resume =  list.get(i);
            System.out.println(resume);
        }

    }
    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where (resume0_.name like ? escape ?) and resume0_.address=?
     * Resume{id=2, name='李四', address='上海', phone='151000000'}
     * @author lane
     * @date 2021/4/9 下午2:53
     */
    @Test
    public void findByNameLike(){

        List<Resume> byNameLikeAndAddress = resumeDao.findByNameLikeAndAddress("李%", "上海");
        for (int i = 0; i < byNameLikeAndAddress.size(); i++) {
            Resume resume =  byNameLikeAndAddress.get(i);
            System.out.println(resume);

        }

    }
    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ order by resume0_.id desc
     * Resume{id=3, name='王五', address='⼴州', phone='153000000'}
     * Resume{id=2, name='李四', address='上海', phone='151000000'}
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * @author lane
     * @date 2021/4/9 下午3:30
     */
    @Test
    public void findAllSort(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Resume> all = resumeDao.findAll(sort);
        for (int i = 0; i < all.size(); i++) {
            Resume resume =  all.get(i);
            System.out.println(resume);

        }
    }
    /**
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ limit ?
     * Hibernate: select count(resume0_.id) as col_0_0_ from tb_resume resume0_
     * Page 1 of 2 containing com.lagou.edu.pojo.Resume instances
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     * Resume{id=2, name='李四', address='上海', phone='151000000'}
     * @author lane
     * @date 2021/4/9 下午3:33
     */
    @Test
    public void findAllPage(){

        Pageable pageable = PageRequest.of(0,2);
        Page<Resume> all = resumeDao.findAll(pageable);
        System.out.println(all);
        for (int i = 0; i < all.getContent().size(); i++) {
            Resume resume =  all.getContent().get(i);
            System.out.println(resume);

        }
    }
    /**
     * 动态条件封装
     * 匿名内部类
     *
     * toPredicate：动态组装查询条件
     *
     *      借助于两个参数完成条件拼装，，， select * from tb_resume where name='张三'
     *      Root: 获取需要查询的对象属性
     *      CriteriaBuilder：构建查询条件，内部封装了很多查询条件（模糊查询，精准查询）
     *
     *      需求：根据name（指定为"张三"）查询简历
     *
     * Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=?
     * Resume{id=1, name='张三', address='北京', phone='131000000'}
     */
    @Test
    public void testSpecfication(){
        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取name属性
                Path<Object> name = root.get("name");
                //根据获取到的属性构建条件精准查询
                Predicate predicate = criteriaBuilder.equal(name, "张三");
                return predicate;
            }
        };

        Optional<Resume> one = resumeDao.findOne(specification);
        Resume resume = one.get();
        System.out.println(resume);

    }

    @Test
    public void testSpecficationMultiCon(){

        /**
         *  Hibernate: select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=? and (resume0_.address like ?)
         *  Resume{id=1, name='张三', address='北京', phone='131000000'}
         *      需求：根据name（指定为"张三"）并且，address 以"北"开头（模糊匹配），查询简历
         */

        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 获取到name属性
                Path<Object> name = root.get("name");
                Path<Object> address = root.get("address");
                // 条件1：使用CriteriaBuilder针对name属性构建条件（精准查询）
                Predicate predicate1 = criteriaBuilder.equal(name, "张三");
                // 条件2：address 以"北"开头（模糊匹配）
                Predicate predicate2 = criteriaBuilder.like(address.as(String.class), "北%");

                // 组合两个条件
                Predicate and = criteriaBuilder.and(predicate1, predicate2);

                return and;
            }
        };


        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println(resume);
    }

}
