package DesignPatterns;

public class Factory {

    interface Notification {
        public void pushNotification();
    }

    class EmailNotification implements Notification{

        @Override
        public void pushNotification() {

        }
    }

    class SmsNotification implements Notification{

        @Override
        public void pushNotification() {

        }
    }

    class CallNotification implements Notification{

        @Override
        public void pushNotification() {

        }
    }

    public class NotificationFactory {
        public Notification createNotification(String type){
            switch(type){
                case "EMAIL":
                    return new EmailNotification();
                case "SMS":
                    return  new SmsNotification();
                case "CALL":
                    return new CallNotification();
                default:
                    throw new IllegalArgumentException(type);
            }
        }
    }


}
