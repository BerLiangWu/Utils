import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Map;

public class JsonUtils {
   

    //获取指定JSON文件，把内容以Map形式返回
    public Map getIndexList(String filePath) throws IOException {
        File indexFile = new File(filePath);
        FileReader fileReader = new FileReader(indexFile);
        Reader reder = new InputStreamReader(new FileInputStream(indexFile), "utf-8");
        int stringCount = 0;
        StringBuffer strBuff = new StringBuffer();
        while ((stringCount = reder.read()) != -1) {
            strBuff.append((char) stringCount);
        }
        fileReader.close();
        reder.close();
        String indexvalueStr = strBuff.toString();
        if (indexvalueStr.length() != 0) {
            JSONObject indexListjsonObject = JSONObject.parseObject(indexvalueStr);
            Map<String,Object> indexValuemap = indexListjsonObject;
            return indexValuemap;
        } else {
            return null;
        }
    }


    //把JSON内容写到本地IO Map形式写入
    public void writeIndexFile(String FilePath,Map indexList) {
        JSONObject json = new JSONObject(indexList);
        File indexFile=new File(FilePath);
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(indexFile);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");
            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(String.valueOf(json));
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

     //把JSON内容写到本地IO String形式写入
     public void writeIndexFile(String FilePath,String indexList) {
        JSONArray strArry=JSON.parseArray(indexList);
        File outFile=new File(FilePath);
        try{
            outFile.createNewFile();
            FileOutputStream fileOutputStream=new FileOutputStream(FilePath);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");
            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(String.valueOf(strArry));
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}