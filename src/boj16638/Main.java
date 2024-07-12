package boj16638;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

   int N;
   int finalResult;
   String[] arr;
   
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

   public void solution() throws IOException {
	   N = Integer.parseInt(br.readLine());
	   arr = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
	   
	   finalResult = Integer.MIN_VALUE;

	   //ù��° dfs�� �Ǿ�� ��
	   int[] firstSelect = new int[N];
	   
	   for (int i = 0; i < N; i++) {
		   if (arr[i].equals("*")) {
			   firstSelect[i] = 2;
		   }
	   }
	   
	   dfs(1, firstSelect);
	   
	   pl(finalResult);	   
//	   pl(Arrays.toString(arr));
   }
   
   public void calculation(int[] select) {
//	   pl(String.format("[calculation] select : %s", Arrays.toString(select)));
	   String[] tmpArr = arr.clone();
	   
	   for (int i = 1; i < N; i += 2) {//1�� �켱���� ��� ó��
		   if (select[i] == 1) {
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   
	   for (int i = 1; i < N; i += 2) {
		   if (select[i] == 2) {//�����ڸ� ã�Ҵٴ� �ǹ�
			   //�� �ٷ� �տ� �� ã��
			   //�� �ٷ� �ڿ� �� ã��
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   
	   for (int i = 1; i < N; i += 2) {
		   if (select[i] != 1 && select[i] != 2) {//��ȣ �Ǵ� *�� �ƴ� ����
			   //�� �ٷ� �տ� �� ã��
			   //�� �ٷ� �ڿ� �� ã��
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   //��� ���� ����
//	   pl(String.format("[��翬��������] tmpArr : %s", Arrays.toString(tmpArr)));
	   for (int i = 0; i < N; i ++) {
		   if (tmpArr[i] != null) { 					   
			   finalResult = Math.max(Integer.parseInt(tmpArr[i]), finalResult);
		   }
	   }
   }
   
   public String fn(String[] tmpArr, int i, boolean flag) {
//	   pl(String.format("[fn] tmpArr: %s, i: %d, flag : %b", Arrays.toString(tmpArr), i, flag));
	   if (!flag) {
		   for (int j = i - 1; j >= 0; j--) {
			   if (tmpArr[j] != null 
					   && !tmpArr[j].equals("+")
					   && !tmpArr[j].equals("-") 
					   && !tmpArr[j].contentEquals("*")) {
				   String tmpResult = tmpArr[j]; 
				   tmpArr[j] = null;
				   return tmpResult;
			   }
		   }
	   } else {
		   for (int j = i + 1; j >= 0; j++) {
			   if (tmpArr[j] != null 
					   && !tmpArr[j].equals("+")
					   && !tmpArr[j].equals("-") 
					   && !tmpArr[j].contentEquals("*")) {
				   String tmpResult = tmpArr[j]; 
				   tmpArr[j] = null;
				   return tmpResult;
			   }
		   }
	   }
	   return null;
   }
   
   public void dfs(int index, int[] select) {
	   if (index >= N) {//�̹� ���� ��
		   calculation(select);//�������� ������ ��
		   return;
	   }

	   //�迭�� ����..
	   int[] newSelect = select.clone();
	   
	   //��ȣ�� ģ��
	   select[index] = 1;
	   dfs(index + 4, select);
	   
	   //��ȣ�� ġ�� �ʴ´�
	   dfs(index + 2, newSelect);
   }
   
   public String cal(String a, String b, String func) {
//	   pl(String.format("[cal] a: %s, b: %s, func: %s", a, b, func));
	   int n1 = ip(a);
	   int n2 = ip(b);
	   switch(func) {
		   case "+" :
			   return String.valueOf(n1 + n2);
		   case "-" :
			   return String.valueOf(n1 - n2);
		   case "*" :
			   return String.valueOf(n1 * n2);
		   default :
			   return "0";
	   }
	   
   }
   
   public static int ip(String s) {
	   return Integer.parseInt(s);
   }
   
   public static void main(String[] args) throws IOException {
      new Main().solution();
   }
   
   public void pl(Object o) {
      System.out.println(o);
   }
   
   public void deepPl(int[][] l) {
      for(int[] lTmp : l) {
         pl(Arrays.toString(lTmp));
      };
   }
}
 