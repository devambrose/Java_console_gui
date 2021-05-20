// Scanner class
package app.smarthomes;
import java.util.Scanner;

public class Dashboard
 {

   /*this class contains the main functions for the interface*/
   public static void main(String []args) {
      runSystem(false);
   }
   public static void runSystem(boolean status){
     while(!status){

       String p=getStringInput("Do you wish to continue Yes | No ?");

       if(p=="Yes" | p=="Y" | p=="yes" | p=="y"){
         status=true;
       }else{
         Scanner in = new Scanner(System.in);

         boolean hasdata=false;

         int number_of_rooms=0;

         number_of_rooms=getIntInput("How many rooms are there in the house?");

         //System.out.println("You entered string " + s);

         int number_of_plugs=getIntInput("How many plugs do you want to place in this property?");

         Room[] rooms=new Room[number_of_rooms];

         for(int i =0; i< number_of_rooms;i++){
             rooms[i]=new Room(getStringInput("Please provide a name for your "+(ordinal(i+1))+" room"),1+i);
         }

         System.out.println("Lets assign now each room a plug ; these are the available rooms");

         String leiteral=" Available rooms : ";

         for(int i =0; i< number_of_rooms;i++){
             leiteral+=" "+rooms[i].toString()+"\n";
         }

         System.out.println(leiteral);

         System.out.println("Using the above list, please select the room for this plug (integer only)");

         SmartPlug[] smart=new SmartPlug[number_of_plugs];

         smart=updateRoomPlugs(rooms,number_of_plugs);

         plugManagementSection(smart,number_of_plugs);
       }
       runSystem(status);
     }
   }
   public static void showPlugs(SmartPlug[] plug,int n){
     int k=0;

     String request=" ";

     while(k<n){

       request+=""+String.valueOf(n)+"";

       k++;
     }
     System.out.println(request);
   }
   public static void plugManagementSection(SmartPlug[] plugs,int n){
     showPlugs(plugs,n);

     int plug=getIntInput("Select SmartPlug");

     String request="PLUG LEVEL OPTIONS\n"+
     "1 - Switch plug off\n"+
     "2 - Switch plug on\n"+
     "3 - Change attached device\n"+
     "4 - Move plug to different room\n Select an option";

     int descision= getIntInput(request);

     try{
       SmartPlug current=plugs[plug-1];

       switch(descision){
         case 1:
         current.getDevice().setState(false);
         break;
         case 2:
         current.getDevice().setState(true);
         break;
         case 3:
         //Room tron=new Room(updateRoomPlug(current.getRoom()));

         //current.setRoom(tron);
         break;
         case 4:

         break;
         default:
          plugManagementSection(plugs,n);
          break;
       }
       showPlugs(plugs,n);

     }catch(ArrayIndexOutOfBoundsException e){
       System.out.println("select a valid plug");
       plugManagementSection(plugs,n);
     }

   }

   public static SmartPlug[] updateRoomPlugs(Room[] rooms,int n){

     SmartPlug[] smart=new SmartPlug[n];

     for(int i =0; i< n;i++){
         int element=getIntInput("Room Number:");

         SmartPlug plug =new SmartPlug(i);

         Room room=updateRoomPlug(rooms[element-1]);

         plug.setRoom(room);

         plug.setDevice(room.getDevice());

         smart[i]=plug;

         System.out.println(plug.toString());
      }
     return smart;
   }
   public static Room updateRoomPlug(Room room){
     String request=("AVAILABLE DEVICE LIST OPTIONS"+
     "These are standard devices that can be attached to"+
     "the smart plug:\n"+
     "1-Lamp\n"+
     "2-TV\n"+
     "3-Computer\n"+
     "4-Phone Recharger\n"+
     "5-Heater\n"+
     "Using the above list, please select the device to attach\n"+
     "to the smart plug (integer only)");

     int selection=getIntInput(request);

     String device="";

     switch(selection){
       case 1:
         device="Lamp";
         break;
       case 2:
          device="TV";
          break;
       case 3:
          device="Computer";
          break;
       case 4:
         device="Phone Recharger";
         break;
        case 5:
         device="Heater";
         break;
        default:
         updateRoomPlug(room);
     }

     room.setDevice(new Device(device,true));

     return room;
   }
   public static String getStringInput(String request){

     Scanner in = new Scanner(System.in);

     System.out.println(request);

     String s = in.nextLine();

     return s;
   }
   public static int getIntInput(String request){//get numeric value from the user

     Scanner in = new Scanner(System.in);

     System.out.println(request);

     String s = in.nextLine();

     int i=0;
     try {
       i=Integer.parseInt(s);
     }catch(NumberFormatException e) {
       System.out.println("please enter a valid numeric input");
       i=getIntInput(request);
     }
     return i;
   }
   public static String ordinal(int i) {
    String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
    switch (i % 100) {
    case 11:
    case 12:
    case 13:
        return i + "th";
    default:
        return i + suffixes[i % 10];

    }
}
}

public class Room{
  private String name;
  private Device device;
  private int id;

  public Room(String name,int id){
    this.name=name;
    this.id=id;
  }
  public int getId(){
    return id;
  }
  public void setName(String name){
    this.name =name;
  }
  public void setDevice(Device device){
   this.device = device;
  }

  public String getName(){
    return this.name;
  }
  public Device getDevice(){
    return this.device;
  }
  public String toString(){
    return this.id +" - "+this.name ;
  }
}
public class Device{
  private String name;
  private boolean state;

  public Device(String name,boolean state){
    this.name=name;
    this.state=state;
  }
  public void setName(String name){
    this.name=name;
  }
  public void setState(boolean state){
    this.state=state;
  }
  public String getName(){
    return this.name;
  }
  public boolean getState(){
    return this.state;
  }
}
public class SmartPlug{
  private Room room;
  private Device device;

  private int id;

  public SmartPlug(int id){
    this.id=id;
  }
  public void setDevice(Device device){
    this.device=device;
  }
  public Device getDevice(){
   return this.device;
  }
  public void setRoom(Room room){
    this.room=room;
  }
  public Room getRoom(Room room){
    return this.room;
  }
  public int getId(){
    return this.id;
  }
  public String toString(){
    return "SmartPlug | attached to: "+this.device.getName()+"| room :"+this.room.getName()+" ID :"+this.getId()
    +"status :"+(this.device.getState() ? "on": "off");
  }
  public void switchPlug(){
    boolean status= this.device.getState();

    this.device.setState(!status);
  }
}
