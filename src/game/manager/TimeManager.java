package game.manager;

public class TimeManager {
    private long startTime;
    private long elapsedTime;
    
    public TimeManager() {
        startTime = System.nanoTime();
    }
    
    public void reset() {
        startTime = System.nanoTime();
    }
    
    public long getTiempoPasado() {
        elapsedTime = System.nanoTime() - startTime;
        return elapsedTime / 1000000; // Convertir nanosegundos a milisegundos
    }
    
    public boolean HapasadoElTiempo(long targetTimeMillis) {
        return getTiempoPasado() >= targetTimeMillis;
    }
}
