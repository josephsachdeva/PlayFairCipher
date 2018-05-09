/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayFairCipher;

/**
 *
 * @author HP PRO
 */
public class PlayFairCipher 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        String key = "helloworld";
        char[][] matrix = new char[5][5];
        //char ch1;
        int loc;
        boolean [] flag = new boolean[25];
        String alphabets= "abcdefghiklmnopqrstuvwxyz";
        for(int i=0; i<25; i++)
        {
            flag[i]=false;
        }
        int length = 0;
        
        char[] ch = key.toCharArray();
        char alpha[] = alphabets.toCharArray();
        
        //key matrix code starts here
        int first = 0;
        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++)
            {
                if(length < key.length())
                {
                   // System.out.println("1");
                    for(int k=0; k<key.length(); k++)
                    {
                        length++;
                        //System.out.println("2");
                        loc = alphabets.indexOf(ch[k]);
                        if(!flag[loc])
                        {
                            //System.out.println("3");
                            //ch1 = ch[k];
                            
                            flag[loc] = true;
                            matrix[r][c] = ch[k];
                            
                            if(c>=4)
                            {
                                break;
                            }
                            c++;
                            
                        }
                    }
                }
                else
                {
                	if(first == 0) {
                		first++;
                		c--;
                	}
                    //System.out.println("4");
                    for(int row=0; row<25; row++)
                    {
                        //System.out.println("5");
                        loc = alphabets.indexOf(alpha[row]);
                        if(!flag[loc])
                        {
                            //ch1 = ch[row];
                            //System.out.println("6");
                            
                            flag[loc] = true;
                            matrix[r][c] = alpha[row];
                            c++;
                            
                            if(c>=4)
                            {
                                c--;
                                break;
                            }
                            
                        }
                    }
                }
            }
        }
        System.out.println("The key array is:-");
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5;j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
//        System.out.println(flag[24]+"-"+alpha[24]);
//        System.out.println(flag[23]+"-"+alpha[23]);
        
      //Cipher text code starts here
        String pt = "playfaircipherplayfaircipher";
        if(pt.length() % 2 != 0){
            pt = pt+"x";
        }
        
        char[] plain = pt.toCharArray();
        char[] ct = new char[40];
        char fst, second;
        int frow=0, fcol=0, srow=0, scol=0, temp=0, ptl=0;
        boolean frst=false, scnd = false;
        for(int l = 0; l<plain.length; l++)
        {
            fst = plain[l];
            second = plain[l+1];
            l++;
            for(int row = 0; row<5; row++){
                for(int col=0; col<5; col++){
                    if(fst == matrix[row][col]){
                        frow = row;
                        fcol = col;
                        frst = true;
                    }
                    if(second == matrix[row][col]){
                        srow = row;
                        scol = col;
                        scnd = true;
                    }
                    if(frst && scnd){
                        if(fcol != scol && frow != srow){
                            ct[ptl] = matrix[frow][scol];
                            ptl++;
                            ct[ptl] = matrix[srow][fcol];
                            ptl++;
                            frst = false;
                            scnd = false;
                        }
                        else if(fcol == scol){
                            if(fcol == 4){
                                fcol=-1;
                            }
                            if(scol == 4){
                                scol =-1;
                            }
                            ct[ptl] = matrix[frow][fcol+1];
                            ptl++;
                            ct[ptl] = matrix[srow][scol+1];
                            ptl++;
                            frst = false;
                            scnd = false;
                        }
                        else if(frow == srow){
                            if(frow == 4){
                                frow = -1;
                            }
                            if(srow == 4){
                                srow = -1;
                            }
                            ct[ptl] = matrix[frow+1][fcol];
                            ptl++;
                            ct[ptl] = matrix[srow+1][scol];
                            ptl++;
                            frst = false;
                            scnd = false;
                        }
                    }
                }
            }
        }
        System.out.println("The Plain text is:- "+pt);
        System.out.print("The Cipher text is:- ");
        for(int locf = 0; locf<ct.length; locf++){
            System.out.print(ct[locf]);
        }
        System.out.println();
    }
}  