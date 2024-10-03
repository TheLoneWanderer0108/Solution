public class Main {
    public static void main(String[] args) {
        // Parte 1: Rotacion de arreglo
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int t = 3;
        
        System.out.println("Original:");
        printArray(arr);
        
        rotateArray(arr, t);
        
        System.out.println("Rotado:");
        printArray(arr);
        
        // Parte 2: Lista enlazada de estudiantes
        LinkedList list = new LinkedList();
        list.add(new Student(1, "Est 1", 3.0));
        list.add(new Student(2, "Est 2", 3.5));
        list.add(new Student(3, "Est 3", 2.5));
        
        System.out.println("\nEstudiantes originales:");
        list.printList();
        
        list.sortStudents(true);
        System.out.println("\nEstudiantes ordenados:");
        list.printList();
    }
    
    static void rotateArray(String[] arr, int t) {
        int n = arr.length;
        int mid = n / 2;
        
        for (int i = 0; i < t; i++) {
            String temp = arr[0];
            for (int j = 0; j < mid - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[mid - 1] = temp;
            
            temp = arr[n - 1];
            for (int j = n - 1; j > mid; j--) {
                arr[j] = arr[j - 1];
            }
            arr[mid] = temp;
        }
    }
    
    static void printArray(String[] arr) {
        for (String s : arr) System.out.print(s + " ");
        System.out.println();
    }
}

class Student {
    int matricula;
    String nombre;
    double indice;
    Student next;
    
    Student(int matricula, String nombre, double indice) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.indice = indice;
    }
}

class LinkedList {
    Student head;
    
    void add(Student student) {
        if (head == null) {
            head = student;
            return;
        }
        Student current = head;
        while (current.next != null) current = current.next;
        current.next = student;
    }
    
    void sortStudents(boolean ascending) {
        head = mergeSort(head, ascending);
    }
    
    private Student mergeSort(Student h, boolean asc) {
        if (h == null || h.next == null) return h;
        
        Student middle = getMiddle(h);
        Student nextOfMiddle = middle.next;
        middle.next = null;
        
        Student left = mergeSort(h, asc);
        Student right = mergeSort(nextOfMiddle, asc);
        
        return merge(left, right, asc);
    }
    
    private Student merge(Student a, Student b, boolean asc) {
        Student result = null;
        if (a == null) return b;
        if (b == null) return a;
        
        if (asc ? (a.indice <= b.indice) : (a.indice >= b.indice)) {
            result = a;
            result.next = merge(a.next, b, asc);
        } else {
            result = b;
            result.next = merge(a, b.next, asc);
        }
        return result;
    }
    
    private Student getMiddle(Student h) {
        if (h == null) return h;
        Student slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    void printList() {
        Student current = head;
        while (current != null) {
            System.out.println(current.matricula + " " + current.nombre + " " + current.indice);
            current = current.next;
        }
    }
}
