package algorithms.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSumSolution {

    public List<List<Integer>> threeSum(int[] a) {

        List<List<Integer>> res=new ArrayList();

        Arrays.sort(a);

        for(int k=0; k<a.length-2; k++){

            int i=0, j=a.length-1;

            while(i<j){

                if(a[i]+a[j]+a[k]==0){

                    List<Integer> list = new ArrayList<>();

                    list.add(a[i]);

                    list.add(a[j]);

                    list.add(a[k]);

                    boolean shouldAdd = true;

                    for(List<Integer> index : res)

                    {

                        if(index.contains(a[i]) && index.contains(a[j]) && index.contains(a[k]))

                        {

                            shouldAdd = false;

                        }

                    }



                    if(shouldAdd)

                    {

                        res.add(list);

                    }

                }

                else if(a[i]+a[j]+a[k] < 0) i++;

                else if(a[i]+a[j]+a[k] > 0) j--;

            }

        }

        return res;

    }

}