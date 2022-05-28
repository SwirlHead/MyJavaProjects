package bombgame;



public class MyGameThread_2 extends Thread{
		MyGameGUI_2 mlc;// коммуникация между классами
		long sped;// adding for task4
		boolean ankor = true;
		
		
		MyGameThread_2(MyGameGUI_2 mlc, float s){
			this.mlc = mlc;
			sped=(long)s;// adding for task4
		}
		
		public void stopMe() {
			ankor=false;
			System.out.println("STOP!!!");

			
		}
		
		
		
		@Override
		public void run() {
			while( ankor ){
				try {
					
					mlc.ChangeLabel();
					
					Thread.currentThread().sleep(sped);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			

}
