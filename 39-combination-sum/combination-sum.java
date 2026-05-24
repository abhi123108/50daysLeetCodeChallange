class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
       List<List<Integer>> List=new ArrayList<>();
       trackMe(List, new ArrayList<>(),candidates,target,0);
       return List; 
    }
    private void trackMe (List<List<Integer>> List , List <Integer> tempList, int[]nums,int target , int start){
        if(target<0) return;
        else if (target==0) List.add(new ArrayList<>(tempList));
        else{
            for(int i=start; i<nums.length; i++){
                tempList.add(nums[i]);
                trackMe(List,tempList,nums,target-nums[i],i);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}