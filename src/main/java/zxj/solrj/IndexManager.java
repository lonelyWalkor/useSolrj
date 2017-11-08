package zxj.solrj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 增，删，改
 * @author Administrator
 *
 */
public class IndexManager {

	private String serverUrl = "http://127.0.0.1:8080/solr/core1";
	/**
	 * 增加与修改<br>
	 * 增加与修改其实是一回事，只要id不存在，则增加，如果id存在，则是修改
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void upadteIndex() throws SolrServerException, IOException{
		//已废弃的方法
		//HttpSolrServer server = new HttpSolrServer("http://192.168.1.4:8080/solr/core1");
		//创建
		HttpSolrClient client = new  HttpSolrClient(serverUrl);
		SolrInputDocument doc = new SolrInputDocument();
		
		doc.addField("id", "zxj2");
		doc.addField("product_name", "javaWEB技术");
		doc.addField("product_catalog", "1");
		doc.addField("product_catalog_name", "书籍");
		doc.addField("product_price", "11");
		doc.addField("product_description", "这是一本好书");
		doc.addField("product_picture", "图片地址");
		
		client.add(doc);
		//一定要记得提交，否则不起作用
		client.commit();
		
		client.close();
	}
	
	
	/**
	 * 删除索引
	 * @throws Exception
	 */
	@Test
	public void deleteIndex()throws Exception{
		HttpSolrClient client = new  HttpSolrClient(serverUrl);
		
		//1.删除一个
		client.deleteById("zxj1");
		
		//2.删除多个
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		client.deleteById(ids);
		
		//3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
		client.deleteByQuery("id:zxj1");
		
		//4.删除全部，删除不可恢复
		client.deleteByQuery("*:*");
		
		//一定要记得提交，否则不起作用
		client.commit();
		client.close();
	}
}
