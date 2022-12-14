public class UserInfo {
    public String name;
    public String sex;
    public long time;
    public UserInfo(String name,String sex,long time){
        this.name=name==null?"null":name;
        this.sex=sex==null?"null":sex;
        this.time=time;
    }
    public String toJson(){
        return String.format("{\"name\":\"%s\",\"sex\":\"%s\"}",name,sex);
    }
}
