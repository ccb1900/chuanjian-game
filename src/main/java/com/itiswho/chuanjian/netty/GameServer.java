package com.itiswho.chuanjian.netty;

import com.itiswho.chuanjian.netty.NettyServer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.InputStream;

public class GameServer {
    /**
     * @param args
     * @throws Exception
     * @url https://netty.io/wiki/index.html
     */
    public static void main(String[] args) throws Exception {
        NettyServer.start(7002);
//        File f = new File("src/main/java/com/itiswho/chuanjian/mybatis/mybatis-config.xml");
//
//        System.out.println(f.getAbsolutePath());
////        String resource = "./src/main/java/com/itiswho/chuanjian/mybatis/mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(f.getAbsolutePath());
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();


//        try {
//            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog = mapper.selectBlog(101);
//        } finally {
//            session.close();
//        }
    }
}
