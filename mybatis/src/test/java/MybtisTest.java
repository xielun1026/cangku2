import itcast.domain.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybtisTest {
    @Test
    public void Test() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = build.openSession();
        List<Dept> objects = sqlSession.selectList("userFind.findUser");
        System.out.println(objects);
        sqlSession.close();
    }
}
