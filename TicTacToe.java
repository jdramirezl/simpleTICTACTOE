import java.util.Scanner;

class tictactoe {
    private String tablero[][] = new String[3][3];
    private String ganador = "";
    private String turno = "x";
    private final String redChar = "\u001B[31m";
    private final String greenBackground = "\u001B[42m";
    private final String blackBackground = "\u001B[40m";
    private final String resetChar = "\u001B[0m";

    public void llenarMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "0";
            }
        }
    }

    public void setTurno() {
        if (this.turno.equals("x")) {
            this.turno = "y";
        } else {
            this.turno = "x";
        }
    }

    public void mostrarTablero() {
        for (String[] fila : tablero) {
            for (String columna : fila) {
                System.out.print(columna + " ");
            }
            System.out.print("\n");
        }
    }

    public String hayGanador() {
        this.ganador = "";

        // filas
        for (int filas = 0; filas < 3; filas++) {
            if (tablero[filas][0].equals(tablero[filas][1]) && tablero[filas][1].equals(tablero[filas][2])
                    && !tablero[filas][1].equals("0")) {
                this.ganador = tablero[filas][0];
                tablero[filas][0] = this.redChar + this.greenBackground + tablero[filas][0] + this.blackBackground
                        + this.resetChar;
                tablero[filas][1] = this.redChar + this.greenBackground + tablero[filas][1] + this.blackBackground
                        + this.resetChar;
                tablero[filas][2] = this.redChar + this.greenBackground + tablero[filas][2] + this.blackBackground
                        + this.resetChar;
            }
        }

        // Columnas
        for (int columnas = 0; columnas < 3; columnas++) {
            if (tablero[0][columnas].equals(tablero[1][columnas]) && tablero[1][columnas].equals(tablero[2][columnas])
                    && !tablero[0][columnas].equals("0")) {
                this.ganador = tablero[0][columnas];
                tablero[0][columnas] = this.redChar + this.greenBackground + tablero[0][columnas] + this.blackBackground
                        + this.resetChar;
                tablero[1][columnas] = this.redChar + this.greenBackground + tablero[1][columnas] + this.blackBackground
                        + this.resetChar;
                tablero[2][columnas] = this.redChar + this.greenBackground + tablero[2][columnas] + this.blackBackground
                        + this.resetChar;
            }
        }

        // Diagonales
        if (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) && !tablero[1][1].equals("0")) {
            this.ganador = tablero[0][0];
            tablero[1][1] = this.redChar + this.greenBackground + tablero[1][1] + this.blackBackground + this.resetChar;
            tablero[2][2] = this.redChar + this.greenBackground + tablero[2][2] + this.blackBackground + this.resetChar;
            tablero[0][0] = this.redChar + this.greenBackground + tablero[0][0] + this.blackBackground + this.resetChar;

        } else if (tablero[2][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[0][2])
                && !tablero[1][1].equals("0")) {
            this.ganador = tablero[1][1];
            tablero[1][1] = this.redChar + this.greenBackground + tablero[1][1] + this.blackBackground + this.resetChar;
            tablero[2][0] = this.redChar + this.greenBackground + tablero[2][0] + this.blackBackground + this.resetChar;
            tablero[0][2] = this.redChar + this.greenBackground + tablero[0][2] + this.blackBackground + this.resetChar;
        }

        return this.ganador;
    }

    public void jugar() {
        int fila, columna;
        int nturno = 0;
        Scanner entrada = new Scanner(System.in);
        llenarMatriz();

        while (this.ganador.isEmpty()) {
            System.out.println("Jugador " + this.turno + " elija la posicion a jugar: ");

            System.out.print("Fila: ");
            fila = entrada.nextInt();
            System.out.print("Columna: ");
            columna = entrada.nextInt();

            if (fila < 3 && columna < 3 && (!tablero[fila][columna].equals("x"))
                    && (!tablero[fila][columna].equals("y"))) {
                tablero[fila][columna] = this.turno;
                nturno++;

                if (!hayGanador().equals("")) {
                    System.out.println("Felicidades! Gano el jugador " + this.turno);
                    mostrarTablero();
                    break;
                } else if (hayGanador().equals(" ") && nturno == 9) {
                    System.out.println("Empate!");
                    mostrarTablero();
                    break;
                }

                setTurno();
            } else {
                System.out.println("La posicion ya esta ocupada o ingreso un valor mayor a 2, repita");
            }

            mostrarTablero();

        }

        entrada.close();
    }

}
