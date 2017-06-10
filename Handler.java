import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**Updates all objects and renders them**/
public class Handler
{
    public List<GameObject> objectList = new ArrayList<GameObject>();       /*Linked List Slow for accessing random elements, O(n) vs O(1) for ArrayList
                                                                          Fast for removal/insertion of elements, O(1) vs O(n) " "*/
    public void tick()  //What to do next frame
    {
        //This is expensive for LinkedLists. Replace with an iterator later.
        for(int i = 0; i<objectList.size(); i++){
            GameObject tempObject = objectList.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g)
    {
        for(int i = 0; i<objectList.size(); i++){
            try{
                GameObject tempObject = objectList.get(i);
                
                tempObject.render(g);
            }
            catch(NullPointerException e)
            {
                continue;
            }
        }
    }
    
    public void addObject(GameObject object){
        objectList.add(object);
    }
    public void removeObject(GameObject object){
        objectList.remove(object);
    }
    public void removeObject(ID id){
        for(int i = objectList.size()-1; i>=0; i--){
            GameObject tempObject = objectList.get(i);
            
            if(tempObject.getID().equals(id)){
                objectList.remove(tempObject);
                break;
            }
        }
    }
    
    public Player getPlayer()
    {
        GameObject temp = objectList.get(0);
        if(!temp.getID().equals(ID.Player))
            throw new RuntimeException("Player not first gameobject created");
            
        return (Player) temp;
    }
}
