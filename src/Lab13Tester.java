import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lab13Tester {
    String[] arr1 = { "1 + 100", "(2+1)*(100-100)", "(100+100)*2-1", "2/1", "22+11", "22-11", "22*11-10", "10-22/11", "5%2", "2%5", "3*0+5","3*0/5%5","2*2*2*2*2" };
  String[] an1 = {"101","0","399","2","33","11","232","8","1","2","5","0","32"};
  //No normal
  String[] arr2 = { "3/0", "30", "3%0", "+30+10", "3 + 4 = 3", "*-+", "30+30","","30++","++3--0","AAA" };
  String[] an2={"Invalid Input", "30", "Invalid Input", "Invalid Input", "Invalid Input", "Invalid Input", "60","Invalid Input","Invalid Input","Invalid Input","Invalid Input" };
  String[] arr3 = { "1 + 100", "(2+1)*(100-100)", "(100+100)*2*2*2-1-1-1", "2/1", "22+11", "22-11", "22*11-10", "10-22/11", "5%2", "2%5", "3*0+5","3*0/5%5" };
  String[] an3 = {"(1+100)","((2+1)*(100-100))","(((((((100+100)*2)*2)*2)-1)-1)-1)","(2/1)","(22+11)","(22-11)","((22*11)-10)","(10-(22/11))","(5%2)","(2%5)","((3*0)+5)","(((3*0)/5)%5)"};  
  @Test
    public void testFortest() throws SyntaxError, EvalError {
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]+" --> ");
            assertEquals(an2[i], Lab13.fortestoutput(arr2[i]));
        }
    }
    @Test
    public void testFortestequation() throws SyntaxError, EvalError {
        for (int i = 0; i < arr3.length; i++) {
        
            assertEquals(an3[i], Lab13.fortestequation(arr3[i]));
        }
    }
}
