package com.example.administrator.saolei;

/**封装扫雷的每一个格子*/
public class ItemEntity {

	/**是否显示当前格*/
	private boolean isShow;

	/**当前格的雷数*/
	private int bombCount;

	/**当前格是否为雷*/
	private boolean isbomb;

	/**当前格是否为边界棋盘*/
	private boolean isboundary;

	/**是否为自动显示的雷*/
	private boolean isAutoShow = false;

	/**是否被标记*/
	private boolean isMark;
	/**雷的标记是错误的*/
	private boolean isMarkWrong;

	public boolean isMarkWrong() {
		return isMarkWrong;
	}

	public void setMarkWrong(boolean isMarkWrong) {
		this.isMarkWrong = isMarkWrong;
	}

	public boolean isMark(){
		return isMark;
	}

	public void setMark(boolean isMark){
		this.isMark = isMark;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public int getbombCount() {
		return bombCount;
	}

	public void setbombCount(int bombCount) {
		this.bombCount = bombCount;
	}

	public boolean isbomb() {
		return isbomb;
	}

	public void setbomb(boolean isbomb) {
		this.isbomb = isbomb;
	}

	public boolean isboundary() {
		return isboundary;
	}

	public void setboundary(boolean isboundary) {
		this.isboundary = isboundary;
	}

	public ItemEntity(boolean isShow, int bombCount, boolean isbomb,
					  boolean isboundary) {
		super();
		this.isShow = isShow;
		this.bombCount = bombCount;
		this.isbomb = isbomb;
		this.isboundary = isboundary;
	}

	public ItemEntity(){

	}

	public void setAutoShow(boolean isAutoShow){
		this.isAutoShow = isAutoShow;
	}

	public boolean isAutoShow(){
		return isAutoShow;
	}

}
