package com.itheima.licenne;

import org.apache.commons.io.FileUtils;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class CreateIndex {
    @Test
    /**
     * 创建索引库
     */
    public void create() throws Exception{
        //声明要创建索引库的目录

        Directory directory = FSDirectory.open(new File("G:\\temp\\index").toPath());
        //添加要写的目录和分词器
        IndexWriter writer = new IndexWriter(directory,new IndexWriterConfig());
//        String s = FileUtils.readFileToString(new File("G:\\java视频\\A0.lucene2018\\02.参考资料\\searchsource"),"utf-8");
        File dir = new File("G:\\java视频\\A0.lucene2018\\02.参考资料\\searchsource");
        File[] files = dir.listFiles();
        for (File file : files) {
            String fileName = file.getName();//文件名
            String filePath = file.getPath();//文件的路径
            String fileContent = FileUtils.readFileToString(file, "utf-8");//文件的内容
            long sizeOf = FileUtils.sizeOf(file);//获取文件的大小

            TextField name = new TextField("name", fileName, Field.Store.YES);
            TextField path = new TextField("path", filePath, Field.Store.YES);
            TextField content = new TextField("content", fileContent, Field.Store.YES);
            TextField size = new TextField("size", sizeOf+"", Field.Store.YES);
            Document document = new Document();
            document.add(name);
            document.add(path);
            document.add(content);
            document.add(size);
            writer.addDocument(document);
        }
        writer.close();
    }
    @Test
    public void query() throws Exception{
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("G:\\temp\\index").toPath()));
        //创建indexsearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //创建查询
        Query query = new TermQuery(new Term("content", "spring"));
        //执行查询
        //第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);
        //查询结果的总条数
        System.out.println("查询结果的总条数："+ topDocs.totalHits);
        //遍历查询结果
        //topDocs.scoreDocs存储了document对象的id
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            //scoreDoc.doc属性就是document对象的id
            //根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("name"));
            //System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println("-------------------------");
        }
        //关闭indexreader对象
        reader.close();
    }
    //删除所有
    @Test
    public void delete() throws Exception{

        Directory directory = FSDirectory.open(new File("G:\\temp\\index").toPath());
        //添加要写的目录和分词器
        IndexWriter writer = new IndexWriter(directory,new IndexWriterConfig());
        writer.deleteAll();
    }
//
}
