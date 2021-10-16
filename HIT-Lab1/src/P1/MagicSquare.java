package P1;

import java.io.*;
import java.util.ArrayList;


public class MagicSquare {
	
	public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;){
		 int chr=str.charAt(i);
		 if(i==0&&chr=='-') {//�ж��ǲ��Ǹ���
			 continue;
		 }
		 else if(chr<48 || chr>57)
			 return false;
		   }
		return true;
		}
    
	public static int Numeric(String str){
			int res=0;
			boolean flag=true;
			for(int i=0;i<str.length();i++){
				
				int chr=str.charAt(i);
				if(i==0&&chr=='-') {
				flag=false;
				}
				else {
					res=res*10+chr-'0';
				}
				
			}
			if(flag) return res;
			else return -res;
			}
    
	public static boolean isLegalMagicSquare(String fileName) {
	
		try {
		    File file = new File(fileName);
		      //file��Ϊ�ļ����������֮����ͨ�绰�����ˡ����������Կ�ʼ��绰�ˡ�
		    if(file.isFile() && file.exists()) {
		      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
		      //��Ӧ����Ҫʹ��InputStreamReader()����������н���ղ�װ�����ڴ浱�е�����
		      BufferedReader br = new BufferedReader(isr);
		      //�ǵ�ȻҪת����IO����ʶ�������ѽ
		      String lineTxt = null;
		      ArrayList<String> str=new ArrayList<String>();
		      
		      while ((lineTxt = br.readLine()) != null) {
		      //bufferedReader()��readline(��������ȡtxt�ļ��е�ÿһ������
		          
		    	  str.add(lineTxt);
		      }
		      //System.out.println(str);
		      br.close();
		      //��\t�ָ��ַ���,�ж������Ƿ����
		        ArrayList<String[]> s=new ArrayList<String[]>();
		        int row=str.size();//��
		       // System.out.println(row);
		        int col=0;
		        for(int i=0;i<row;i++) {
		        	String[] temp;
			        String delimeter = "\t" ;  // ָ���ָ��ַ�
			        String strsub=str.get(i);
			        temp = strsub.split(delimeter); // �ָ��ַ���
			        col=temp.length;
			        //System.out.println(col);
			        if(col!=row) {
			        	System.out.println("���в����!");
			        	return false;
			        }
			        else {
			        	
				           s.add(temp);
				          
			        }
			        
			     }
		      //�ж�ÿ���ַ��Ƿ�Ϊ����
		      //ת��Ϊ��������
		       int[][] magic=new int[row][row];
		        for(int i=0;i<row;i++) {
		        	String strsub[]=s.get(i);
		        	for(int j=0;j<col;j++) {
		        		if(isNumeric(strsub[j])){
		        			magic[i][j]=Numeric(strsub[j]);
		        		}
		        		else {
		        			System.out.println("���ݲ�������!");	
		        			return false;
		        		}
		        	}
		        }
		        int n=row;
		        int sum=0;
				int sumsolid=0;//��һ�еĺ�
				for(int i=0;i<n;i++) {
					sumsolid=sumsolid+magic[0][i];
				}
				//row
				for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sum=sum+magic[i][j];
				}
				if(sum==sumsolid) {
					sum=0;
				}
				else {
					System.out.println("��"+i+"�в����");
					return false;
				}
				}
				//col
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						sum=sum+magic[j][i];
					}
					if(sum==sumsolid) {
						sum=0;
					}
					else {
					System.out.println("��"+i+"�в����");	
					return false;
					}
					
				}
				//���Խ���
				for(int i=0;i<n;i++) {
					sum=sum+magic[i][i];
				}
				if(sum==sumsolid) {
					sum=0;
				}
				else {
					System.out.println("���Խ��߲����");	
					return false;	
				}
				
				//���Խ���
				for(int i=0;i<n;i++) {
					sum=sum+magic[i][n-1-i];
				}
				if(sum==sumsolid) {
					sum=0;
				}
				else {
					System.out.println("���Խ��߲����");
					return false;	
				}
				
				
				return true;
		    
		    } 
		    else {
		      System.out.println("�ļ�������!");
		      return false;
		      
		    }
		  } catch (Exception e) {
			    System.out.println("�ļ���ȡ����!");
			    return false;
			  }
		 	
	
	
	
	}
	
	
	public static boolean  generateMagicSquare(int n) {
		try {
		if(n<=0) {
			System.out.println("����Ϊ������");
			return false;
		}
		if(n%2==0) {
			System.out.println("����Ϊż����");
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
		magic[row][col] = i;
			if (i % n == 0)
			  row++;//һ�������Ѿ�����������һ�У������ظ�д�����֡�
			//ÿn������һ�����ڣ���������ѭ����д��������μ�1.
			else {
				if (row == 0)
				  row = n - 1;
				else
				  row--;
				if (col == (n - 1))
				  col = 0;
				else
				  col++;
			} 
		}
		//���magic
		for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++)
		System.out.print(magic[i][j] + "\t");
		System.out.println();
		}

		//��magicд���ļ�src\\P1\\txt\\6.txt
            FileWriter fw = new FileWriter("src\\P1\\txt\\6.txt");
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++)
                    fw.write(magic[i][j]+ "\t");
                fw.write("\n");
            }
            fw.close();
            return true;
        
		}
		catch (IOException e) {
			System.out.println("�ļ�����ʧ�ܣ�");
			return false;
        }
		
		
        }       

		

		
	
	public static void main(String[] args) {
		
		boolean one=isLegalMagicSquare("src\\P1\\txt\\1.txt");
		System.out.println("1"+one);
		boolean two=isLegalMagicSquare("src\\P1\\txt\\2.txt");
		System.out.println("2"+two);
		boolean three=isLegalMagicSquare("src\\P1\\txt\\3.txt");
		System.out.println("3"+three);
		boolean four=isLegalMagicSquare("src\\P1\\txt\\4.txt");
		System.out.println("4"+four);
		boolean five=isLegalMagicSquare("src\\P1\\txt\\5.txt");
		System.out.println("5"+five);
		boolean a=generateMagicSquare(5);
		System.out.println(a);
		boolean six=isLegalMagicSquare("src\\P1\\txt\\6.txt");
		System.out.println("6"+six);
		
	}

}
