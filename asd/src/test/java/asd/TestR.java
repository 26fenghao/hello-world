package asd;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;


public class TestR {
	 	@Test
		public void lsRoot() throws IOException, KeeperException, InterruptedException {
			// TODO Auto-generated method stub
	    	ZooKeeper zKeeper=new ZooKeeper("s101:2181,s102:2181",5000,null);
	    	List<String> list=zKeeper.getChildren("/", null);
	    	for(String m:list){
	    		System.out.println(m);
	    	}
		}
	 
	 @Test
	 public void testLs() throws IOException, KeeperException, InterruptedException{
	 ls("/");
	 }
	 public void ls(String path) throws IOException, KeeperException,
	 InterruptedException{
	 ZooKeeper zKeeper =new ZooKeeper("s101:2181", 5000, null);
	 List<String> list=zKeeper.getChildren(path, null);
	 System.out.println("list:"+list);
	 System.out.println(path);
	 for(String s :list){
	 if(path.equals("/")){
	 ls(path+s);
	 }else{
	 ls(path+"/"+s);
	 }
	 }
	 }


}
