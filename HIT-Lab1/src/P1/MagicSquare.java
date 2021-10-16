package P1;

import java.io.*;
import java.util.ArrayList;


public class MagicSquare {
	
	public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;){
		 int chr=str.charAt(i);
		 if(i==0&&chr=='-') {//判断是不是负数
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
		      //file即为文件句柄。两人之间连通电话网络了。接下来可以开始打电话了。
		    if(file.isFile() && file.exists()) {
		      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
		      //对应的需要使用InputStreamReader()这个方法进行解读刚才装进来内存当中的数据
		      BufferedReader br = new BufferedReader(isr);
		      //那当然要转换成IO可以识别的数据呀
		      String lineTxt = null;
		      ArrayList<String> str=new ArrayList<String>();
		      
		      while ((lineTxt = br.readLine()) != null) {
		      //bufferedReader()的readline(）方法读取txt文件中的每一行数据
		          
		    	  str.add(lineTxt);
		      }
		      //System.out.println(str);
		      br.close();
		      //以\t分割字符串,判断行列是否相等
		        ArrayList<String[]> s=new ArrayList<String[]>();
		        int row=str.size();//行
		       // System.out.println(row);
		        int col=0;
		        for(int i=0;i<row;i++) {
		        	String[] temp;
			        String delimeter = "\t" ;  // 指定分割字符
			        String strsub=str.get(i);
			        temp = strsub.split(delimeter); // 分割字符串
			        col=temp.length;
			        //System.out.println(col);
			        if(col!=row) {
			        	System.out.println("行列不相等!");
			        	return false;
			        }
			        else {
			        	
				           s.add(temp);
				          
			        }
			        
			     }
		      //判断每个字符是否为整数
		      //转化为整数数组
		       int[][] magic=new int[row][row];
		        for(int i=0;i<row;i++) {
		        	String strsub[]=s.get(i);
		        	for(int j=0;j<col;j++) {
		        		if(isNumeric(strsub[j])){
		        			magic[i][j]=Numeric(strsub[j]);
		        		}
		        		else {
		        			System.out.println("数据不是整数!");	
		        			return false;
		        		}
		        	}
		        }
		        int n=row;
		        int sum=0;
				int sumsolid=0;//第一行的和
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
					System.out.println("第"+i+"行不相等");
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
					System.out.println("第"+i+"列不相等");	
					return false;
					}
					
				}
				//主对角线
				for(int i=0;i<n;i++) {
					sum=sum+magic[i][i];
				}
				if(sum==sumsolid) {
					sum=0;
				}
				else {
					System.out.println("主对角线不相等");	
					return false;	
				}
				
				//副对角线
				for(int i=0;i<n;i++) {
					sum=sum+magic[i][n-1-i];
				}
				if(sum==sumsolid) {
					sum=0;
				}
				else {
					System.out.println("副对角线不相等");
					return false;	
				}
				
				
				return true;
		    
		    } 
		    else {
		      System.out.println("文件不存在!");
		      return false;
		      
		    }
		  } catch (Exception e) {
			    System.out.println("文件读取错误!");
			    return false;
			  }
		 	
	
	
	
	}
	
	
	public static boolean  generateMagicSquare(int n) {
		try {
		if(n<=0) {
			System.out.println("输入为负数！");
			return false;
		}
		if(n%2==0) {
			System.out.println("输入为偶数！");
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
		magic[row][col] = i;
			if (i % n == 0)
			  row++;//一个周期已经结束，往下一行，避免重复写入数字。
			//每n个数是一个周期，向上向右循环，写入的数依次加1.
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
		//输出magic
		for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++)
		System.out.print(magic[i][j] + "\t");
		System.out.println();
		}

		//将magic写入文件src\\P1\\txt\\6.txt
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
			System.out.println("文件创建失败！");
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
