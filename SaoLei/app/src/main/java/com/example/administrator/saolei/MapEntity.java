package com.example.administrator.saolei;

import java.util.Random;

/**封装棋盘*/
public class MapEntity {

	ItemEntity[][] Map;
	private int level;
	private int bombCount;
	/**边界格*/
	private ItemEntity boundary = new ItemEntity(false,0,false,true);

	public MapEntity(int level){
		this.level = level;
		Map = new ItemEntity[level+2][level+2];
		bombCount = level*level/10;
		initbomb();
	}

	public void initbomb(){
		//初始化地图
		for(int i = 0;i<level+2;i++){
			for(int j = 0;j<level+2;j++){
				if(i == 0 || j == 0 || i==level+1 || j==level+1){
					Map[i][j] = boundary;
				}else{
					Map[i][j] = new ItemEntity();
				}
			}
		}
		Random rand = new Random(System.currentTimeMillis());
		//分配雷
		for(int x = 0;x<bombCount;x++){
			int i = rand.nextInt(level)+1;
			int j = rand.nextInt(level)+1;
			if(Map[i][j].isbomb()){
				x--;
				continue;
			}else{
				Map[i][j].setbomb(true);
			}
		}
		//初始化格子周围地雷数
		for(int i = 1;i<=level;i++){
			for(int j = 1;j<=level;j++){
				setbombCount(Map[i][j],i,j);
			}
		}
		syso();  //生成棋盘后打印整个棋盘，帮助进行测试
	}

	/*为单元格计算雷的个数*/
	private void setbombCount(ItemEntity entity,int i,int j){
		int count = 0;
		for(int ii = i-1;ii<=i+1;ii++){
			for(int jj = j-1;jj<=j+1;jj++){
				if(Map[ii][jj].isbomb())
					count++;
			}
		}
		entity.setbombCount(count);
	}

	/*获取某个位置的item*/
	public ItemEntity getEntity(int position){
		ItemEntity entity = null;
		entity = Map[(position)/level+1][position%level+1];
		return entity;
	}

	/*判断游戏是否成功*/
	public boolean isWin(){
		int count = 0;
		for(int i = 1;i<=level;i++){
			for(int j = 1;j<=level;j++){
				if(!Map[i][j].isShow()){
					count++;
				}
			}
		}
		return count == bombCount;  //未开启单元格与雷的个数一致，证明游戏胜利
	}

	/*测试方法：打印整个棋盘*/
	private void syso() {
		for(int i = 1;i<=level;i++){
			for(int j = 1;j<=level;j++){
				if(Map[i][j].isbomb()){
					System.out.print("*");
				}else if(Map[i][j].getbombCount() == 0){
					System.out.print("-");
				}else{
					System.out.print(Map[i][j].getbombCount());
				}
			}
			System.out.println();
		}
	}

	/*显示所有雷的方法*/
	public void showbomb(){
		for(int i = 1;i<=level;i++){
			for(int j =1;j<=level;j++){
				if(Map[i][j].isbomb() && !Map[i][j].isShow()){
					Map[i][j].setShow(true);
					Map[i][j].setAutoShow(true);
				}
			}
		}
	}

	/*显示无害区域*/
	public void showsafearea(int position){
		if(position>=level*level || position<0){
			return;
		}
		int i = position/level+1;
		int j = position%level+1;
		if(Map[i][j].isboundary()){
			return;
		}
		if(Map[i][j].getbombCount() != 0 || Map[i][j].isShow()){
			Map[i][j].setShow(true);
			return;
		}
		Map[i][j].setShow(true);
		for(int ii = i-1;ii<=i+1;ii++){
			for(int jj = j-1;jj<=j+1;jj++){
				if(ii<=0 || jj<=0 || ii>=level+1 || jj>=level+1){
					continue;
				}
				showsafearea((ii-1)*level+(jj-1));
			}
		}
	}

	/**修改标记状态（如果标记的不是雷，则修改状态，使其显示错误雷的图片）*/
	public void showMark(){
		for(int i = 1;i<=level;i++){
			for(int j = 1;j<=level;j++){
				if(Map[i][j].isMark() && Map[i][j].isbomb()){
					Map[i][j].setMarkWrong(false);
				}else if(Map[i][j].isMark() && !Map[i][j].isbomb()){
					Map[i][j].setMarkWrong(true);
				}
			}
		}
	}
}
