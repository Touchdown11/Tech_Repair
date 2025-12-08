package Utils;

public class CustomerNotification {

    private final SendEmail sendEmailService;

    public CustomerNotification() {
        this.sendEmailService = new SendEmail();
    }


    public void welcomeEmail(String email, String name) {
        String subject = "Welcome to TechRepair, " + name + "!";
        String messageText = "Dear " + name + ",\n\n" +
                "Thank you for choosing TechRepair. We are excited to assist you with your electronic repair needs. " +
                "Please feel free to reach out to us if you have any questions.\n\n" +
                "Best regards,\nTechRepair Team";
        sendEmailService.sendEmail(email, subject, messageText);
    }

    public void updateOrderState(String email, String name, String orderId, String orderStatus) {
        String subject = "Your Device is Ready for Collection!";
        String messageText = "Dear " + name + ",\n\n" +
                "We are pleased to inform you that your device (Order ID: " + orderId + ") is now ready for collection. " +
                "The current status of your order is: " + orderStatus + ".\n\n" +
                "Thank you for choosing TechRepair.\n\n" +
                "Best regards,\nTechRepair Team";

        sendEmailService.sendEmail(email, subject, messageText);
    }

    public void customEmail(String email, String subject, String messageText) {
        sendEmailService.sendEmail(email, subject, messageText);
    }


    public void repairUpdate(String email, String name, String orderId, String repairDetails) {
        String subject = "Repair Update for Your Device";
        String messageText = "Dear " + name + ",\n\n" +
                "We have an update regarding the repair of your device (Order ID: " + orderId + ").\n\n" +
                "Repair Details: " + repairDetails + "\n\n" +
                "We will notify you once the repair is complete.\n\n" +
                "Best regards,\nTechRepair Team";

        sendEmailService.sendEmail(email, subject, messageText);
    }

    public  void invoiceEmail(String email, String name, String orderId, Double price) {
        String subject = "Invoice for Your Device Repair";
        String messageText = "Dear " + name + ",\n\n" +
                "We have completed the repair of your device (Order ID: " + orderId + ").\n\n" +
                "Total Price: Rs." + price + "\n\n" +
                "Please make the payment at your earliest convenience.\n\n" +
                "Best regards,\nTechRepair Team";

        sendEmailService.sendEmail(email, subject, messageText);
    }
}

