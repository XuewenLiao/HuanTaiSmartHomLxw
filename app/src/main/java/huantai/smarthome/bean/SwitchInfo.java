package huantai.smarthome.bean;

/**
 * description:开关bean
 * auther：joahluo
 * time：2017/8/25 20:17
 */
public class SwitchInfo {
          public int get_id() {
                    return _id;
          }

          public void set_id(int _id) {
                    this._id = _id;
          }

          public String getAddress() {
                    return address;
          }

          public void setAddress(String address) {
                    this.address = address;
          }

          public String getBindgiz() {
                    return bindgiz;
          }

          public void setBindgiz(String bindgiz) {
                    this.bindgiz = bindgiz;
          }

          public int getFlag() {
                    return flag;
          }

          public void setFlag(int flag) {
                    this.flag = flag;
          }

          public String getName() {
                    return name;
          }

          public void setName(String name) {
                    this.name = name;
          }

          public int getType() {
                    return type;
          }

          public void setType(int type) {
                    this.type = type;
          }

          public String getUserid() {
                    return userid;
          }

          public void setUserid(String userid) {
                    this.userid = userid;
          }

          public SwitchInfo() {
                    super();
          }

          private int _id;
          private String name;
          private String address;
          private String bindgiz;
          private String userid;
          private int flag;
          private int type;

          public int getStatus1() {
                    return status1;
          }

          public void setStatus1(int status1) {
                    this.status1 = status1;
          }

          public int getStatus2() {
                    return status2;
          }

          public void setStatus2(int status2) {
                    this.status2 = status2;
          }

          public int getStatus3() {
                    return status3;
          }

          public void setStatus3(int status3) {
                    this.status3 = status3;
          }

          private int status1;
          private int status2;
          private int status3;

          public SwitchInfo(int id, String name, String address, String bindgiz, String userid, int flag, int type) {
                    super();
                    this._id = id;
                    this.name = name;
                    this.address = address;
                    this.bindgiz=bindgiz;
                    this.userid=userid;
                    this.flag = flag;
                    this.type = type;
                    this.status1=0;
                    this.status2=0;
                    this.status3=0;
          }

          public SwitchInfo(String name, String address, String bindgiz, String userid, int flag, int type) {
                    super();
                    this.name = name;
                    this.address = address;
                    this.bindgiz=bindgiz;
                    this.flag = flag;
                    this.userid=userid;
                    this.type = type;
          }
}
