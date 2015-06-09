/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fill.strct;

import java.util.ArrayList;
import java.util.List;

/**
 *queue img
 * @author SSI
 */
public class C_node_queue {
    List<C_node_img> nodedata=null;
/**
 * สร้างคิวว่าง
 */
    public void my_setEmp() {
        nodedata=new ArrayList<C_node_img>();
    }
    /**
     * ดึง node ออกจาก queue ถ้าหมดจะส่ง null
     * @return node ที่อยู่ตำแหน่งแรกของ queue
     */
    public C_node_img my_next(){
        if (nodedata.size() > 0) {
            C_node_img out=nodedata.get(0);
            nodedata.remove(0);
            return out;
        }
        return null;
    }
    /**
     * เพิ่มข้อมูลลง queue
     * @param node 
     */
    public void my_add(C_node_img node){
        if(nodedata==null)my_setEmp();
        nodedata.add(node);
    }

}
