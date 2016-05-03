package android.noelove.loving;

public class Love {
		private String name;
		private String group;
		
		public Love(String name){
			this.name = name;
		}
		//========= NAME
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name = name;
		}
		//========= GROUP
		public String getGroup(){
			return group;
		}
		
		public void setGroup(String group){
			this.group = group;
		}
}
