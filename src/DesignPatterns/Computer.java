package DesignPatterns;

public class Computer {


        int hdd;
        int ram;
        String cpu;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        private Computer(ComputerBuilder builder){
                this.hdd=builder.hdd;
                this.ram=builder.ram;
                this.cpu=builder.cpu;
                this.isBluetoothEnabled=builder.isBluetoothEnabled;
                this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
        }

        public int getHdd() {
                return hdd;
        }

        public int getRam() {
                return ram;
        }

        public String getCpu() {
                return cpu;
        }

        public boolean isGraphicsCardEnabled() {
                return isGraphicsCardEnabled;
        }

        public boolean isBluetoothEnabled() {
                return isBluetoothEnabled;
        }

        public static class ComputerBuilder{
                private int hdd;
                private int ram;
                private String cpu;
                private boolean isGraphicsCardEnabled;
                private boolean isBluetoothEnabled;

                //mandatory hdd and ram
                public ComputerBuilder(int hdd, int ram) {
                        this.hdd = hdd;
                        this.ram = ram;
                }

                public void hdd(int hdd) {
                        this.hdd = hdd;
                }

                public void ram(int ram) {
                        this.ram = ram;
                }

                public void cpu(String cpu) {
                        this.cpu = cpu;
                }

                public void graphicsCardEnabled(boolean graphicsCardEnabled) {
                        isGraphicsCardEnabled = graphicsCardEnabled;
                }

                public void bluetoothEnabled(boolean bluetoothEnabled) {
                        isBluetoothEnabled = bluetoothEnabled;
                }

                public Computer build(){
                        return new Computer(this);
                }
        }

        @Override
        public String toString() {
                return "Computer{" +
                        "hdd=" + hdd +
                        ", ram=" + ram +
                        ", cpu='" + cpu + '\'' +
                        ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
                        ", isBluetoothEnabled=" + isBluetoothEnabled +
                        '}';
        }

        public static void main(String[] args) {
                ComputerBuilder builder = new ComputerBuilder(512,16);
                builder.isBluetoothEnabled=true;
                builder.cpu="Intel";
                Computer computer = builder.build();
                System.out.println(computer);
        }

}


