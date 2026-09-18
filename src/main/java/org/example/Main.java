package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int siguienteId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tarea> tareas = new ArrayList<>();
        int opcion = 0;

        do {
            System.out.println("\n=== TODO LIST ===");
            System.out.println("1. Ver tareas");
            System.out.println("2. Añadir tarea");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción no válida.");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> readTask(tareas);
                case 2 -> addTask(tareas, sc);
                case 3 -> completeTask(tareas, sc);
                case 4 -> deleteTask(tareas, sc);
                case 5 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        sc.close();
    }

    private static void deleteTask(ArrayList<Tarea> tareas, Scanner sc) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        System.out.print("ID de la tarea a eliminar: ");
        if (!sc.hasNextInt()) {
            System.out.println("Número inválido.");
            sc.nextLine();
            return;
        }
        int id = sc.nextInt();

        Tarea tarea = buscarPorId(tareas, id);
        if (tarea != null) {
            tareas.remove(tarea);
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static void addTask(ArrayList<Tarea> tareas, Scanner sc) {
        System.out.print("Título de la tarea: ");
        String titulo = sc.nextLine();

        System.out.print("Descripción de la tarea: ");
        String descripcion = sc.nextLine();

        tareas.add(new Tarea(siguienteId++, titulo, descripcion));
        System.out.println("Tarea añadida.");
    }

    private static void completeTask(ArrayList<Tarea> tareas, Scanner sc) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        System.out.print("ID de la tarea completada: ");
        if (!sc.hasNextInt()) {
            System.out.println("Número inválido.");
            sc.nextLine();
            return;
        }
        int id = sc.nextInt();

        Tarea tarea = buscarPorId(tareas, id);
        if (tarea != null) {
            tarea.completar();
            System.out.println("Tarea marcada como completada.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static Tarea buscarPorId(ArrayList<Tarea> tareas, int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    private static void readTask(ArrayList<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        System.out.println("\nTareas:");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            String estado = tarea.isCompletada() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + estado + " ID: " + tarea.getId() + "\nTITULO: " + tarea.getTitulo() + "\nDESCRIPCION: " + tarea.getDescripcion() + "\n");
        }
    }
}
