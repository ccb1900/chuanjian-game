package com.itiswho.ccb.server;

public class GServer {
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
