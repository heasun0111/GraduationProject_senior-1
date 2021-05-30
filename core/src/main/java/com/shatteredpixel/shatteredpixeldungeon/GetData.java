package com.shatteredpixel.shatteredpixeldungeon;

import sun.rmi.runtime.Log;

public class GetData {

    //static으로 클래스 내 변수 선언
    static GetData getData;
    static int depth=0;
    static int hp=0;
    static int ht=0;
    static int damage=0;
    static int attackDamage=0;

    static int preHp=20;
    static int postHp=0;
    static int healing=0;

    static int monster=0;
    static int moving=0;
    static int movingDistance=0;
    static int movingDistanceCheck=0;
    static int exp=0;


    //정보가 바뀌면 누적해주는 메소드
    public static void setDepth(int depth) {
        GetData.depth += depth;
    }

    public static void setHp(int hp) {
        GetData.hp = hp;
    }

    public static void setHt(int ht) {
        GetData.ht = ht;
    }

    public static void setDamage(int damage) {
        GetData.damage += damage;
    }

    public static void setAttackDamage(int attackDamage) {
        GetData.attackDamage += attackDamage;
    }

    public static void setHealing(int postHp) {
        if(preHp<postHp){
            healing += (postHp-preHp);
        }
        preHp = postHp;

    }

    public static void setMonster(int monster) {
        GetData.monster += monster;
    }

    public static void setMoving(int moving) {
        GetData.moving += moving;
    }

    public static void setMovingDistance(int movingDistance) {
        GetData.movingDistance += movingDistance;
    }

    public static void setMovingDistanceCheck(int movingDistanceCheck) {
        GetData.movingDistanceCheck = movingDistanceCheck;
    }

    public static void setExp(int exp) {
        GetData.exp += exp;
    }

    //바뀐 변수를 다시 return하여 가져오는 메소드
    public static int getDepth() {
        return depth;
    }

    public static int getHp() {
        return hp;
    }

    public static int getHt() {
        return ht;
    }

    public static int getDamage() {
        return damage;
    }

    public static int getAttackDamage() {
        return attackDamage;
    }

    public static int getHealing() {
        return healing;
    }

    public static int getMonster() {
        return monster;
    }

    public static int getMoving() {
        return moving;
    }

    public static int getMovingDistance() {
        return movingDistance;
    }

    public static int getMovingDistanceCheck() {
        return movingDistanceCheck;
    }

    public static int getExp() {
        return exp;
    }

    //층 이동 시 다시 reset하여 정보 초기화
    public void reset(){
        depth = 0;
        hp=0;
        ht=0;
        damage=0;
        attackDamage=0;

        preHp=0;
        postHp=0;
        healing=0;

        monster=0;
        moving=0;
        movingDistance=0;
        movingDistanceCheck=0;
        exp=0;
    }

    //원래 변수의 값과 다르면 instance를 가져온다.
    static public GetData getInstance(){
        if(getData!=null){
            return getData;
        }else
            return new GetData();
    }

    private GetData(){

    }

    // 다음 단계로 가는 것 이전의 저장된 데이터 출력
    public void goNextLvl(){
        int D = depth+1;
        print();
        reset();
        depth = D;
    }

    //데이터 출력
    public void print(){
        //System.out.println("!!Depth : "+depth);
        System.out.println("!!HP : "+hp);
        System.out.println("!!HT : "+ht);
        System.out.println("!!Damage : "+damage);
        System.out.println("!!AttackDamage : "+attackDamage);
        System.out.println("!!Healing : "+healing);
        System.out.println("!!Monster : "+monster);
        System.out.println("!!Moving : "+moving);
        //System.out.println("!!MovingDistance : "+movingDistance);
        //System.out.println("!!MovingDistanceCheck : "+movingDistanceCheck);
        System.out.println("!!Exp : "+exp);
    }


}
