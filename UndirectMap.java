package com.company;
import java.util.*;
import java.util.Scanner;

public class UndirectMap {



    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        node_class n_new = new node_class();
        node_class[] array_nodes = new node_class[10000];
        ArrayList<node_class> array_neighbours = new ArrayList<>();
        HashMap <node_class,ArrayList<node_class>> map_undirect = new HashMap<node_class,ArrayList<node_class>>();
        int loop = 0;



        //CREATE NODE
        System.out.println("Say me how many nodes you want to use");
        int n_node = input.nextInt();
        for (int i = 0; i < n_node; i++) {
            array_nodes[i] = addNode(n_new);
            //System.out.println("CONTROLLO " + i + array_nodes[i].getName());
        }



        //CREATE NEIGHBOURS
        for(int i=0;i<n_node;i++) {

            System.out.println("\nSay me how many neighbours have your "+ i + "° node");
            int n_neighbour = input.nextInt();
            while(n_neighbour >= n_node){
                System.out.println("Impossible action, give me a less number");
                n_neighbour = input.nextInt();
            }

            array_neighbours.clear();
            for(int j=0;j<n_neighbour;j++){
                System.out.println("Give me the name of the neighbour");
                String name = input.next();

                while(Objects.equals(array_nodes[i].getName(), name)){
                    System.out.println("Impossible action, insert a new name.");
                    name = input.next();
                }
                for (int z = 0; z < array_neighbours.size(); z++) {
                    if (Objects.equals(array_neighbours.get(z).getName(), name)) {
                        System.out.println("This neighbour is already there, insert a different name.");
                        name = input.next();
                    }
                }

                array_neighbours.add(addNeighbourList(array_nodes,n_node,name));
            }

            map_undirect.put(array_nodes[i],array_neighbours);
        }


        /* I don't know why but the arraylist "array_neighbours" is correct, instead if I put it inside my "map_direct"
           and I print the list inside the map is not the same (all nodes will have the same neighbours of the last node).
           I've checked all the program but I can't find my mistake. Sorry for that. (the same in the "map_undirect").
         */



        //CREATE NEIGHBOURS UNDIRECT
        for(int i=0;i<n_node;i++){
            for(int j=0;j<map_undirect.get(array_nodes[i]).size();j++){
                node_class n_check;
                n_check = map_undirect.get(array_nodes[i]).get(j);
                if(addNeighbourUndirect(n_check, array_nodes, i, map_undirect)==0)
                    map_undirect.get(n_check).add(array_nodes[i]);
            }
        }



        //GET NEIGHBOURS
        node_class n_index = new node_class();
        node_class n_print = new node_class();
        System.out.println("\n");
        n_index = getNeighboursOfNode(array_nodes, n_node);

        for(int i=0;i<map_undirect.get(n_index).size();i++){
            n_print = map_undirect.get(n_index).get(i);
            System.out.println("Neighbour " + i + "° " + n_print.getName());
        }
        if(map_undirect.get(n_index).size() == 0)
            System.out.println("No neighbours");



        //DELETE NEIGHBOUR
        node_class n_index_delete = new node_class();
        node_class n_delete = new node_class();
        System.out.println("\nSay me the name of the neighbour that you want delete");
        String name_delete = input.next();
        n_index_delete = deleteNeighbour(array_nodes, n_node);

        for(int i=0;i<map_undirect.get(n_index_delete).size();i++){
            n_delete = map_undirect.get(n_index_delete).get(i);
            if(Objects.equals(n_delete.getName(), name_delete))
                map_undirect.get(n_index_delete).remove(i);
        }



    }








    public static node_class search_neighbour(node_class[] array, String name, int loops){

        for(int i=0;i<loops;i++){
            if(Objects.equals(array[i].getName(), name) ) {
                return array[i];
            }
        }
        return null;

    }




    public static node_class addNode(node_class n){

        Scanner input = new Scanner(System.in);
        n = new node_class();
        System.out.println("Say me the name of your new node");
        String name = input.next();
        n.setName(name);
        return n;

    }




    /*          I haven't used the addNeighbourg method because for how I've written my program
                that kind of method is useless           */



    public static node_class addNeighbourList(node_class[] array, int loops, String name){

        Scanner input = new Scanner(System.in);
        node_class n_new;
        n_new = search_neighbour(array, name, loops);

        while(n_new == null){
            System.out.println("There isn't a neighbour like that, insert a new name.");
            name = input.next();
            n_new = search_neighbour(array, name, loops);
        }

        return n_new;

    }




    public static node_class getNeighboursOfNode(node_class[] array, int loops){

        Scanner input = new Scanner(System.in);
        System.out.println("Say me the name of the node which you want to find his neighbours");
        String name = input.next();
        node_class n_new;
        n_new = search_neighbour(array, name, loops);

        while(n_new == null){
            System.out.println("The node doesn't exist, insert a new name.");
            name = input.next();
            n_new = search_neighbour(array, name, loops);
        }

        return n_new;
    }




    public static node_class deleteNeighbour(node_class[] array, int loops){

        Scanner input = new Scanner(System.in);
        System.out.println("Say me the name of the node wich you want to delete one of his neighbours");
        String name = input.next();

        while(search_neighbour(array, name, loops) == null){
            System.out.println("The node doesn't exist, insert a new name.");
            name = input.next();
        }

        return search_neighbour(array, name, loops);
    }




    public static int addNeighbourUndirect(node_class n_check, node_class[] array, int i, HashMap <node_class,ArrayList<node_class>> map_undirect){
        int count = 0;
        for(int z=0;z<map_undirect.get(n_check).size();z++) {
            if (Objects.equals(map_undirect.get(n_check).get(z).getName(), array[i].getName()));
                return 1;
        }
        return 0;
    }


}
