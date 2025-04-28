package Service;
import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }


    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageByIdService(int id) {
        return messageDAO.getMessageById(id);
    }


    public Message addMessage(Message message) {
        if(messageDAO.getMessageById(message.getMessage_id()) == null) {
            return messageDAO.insertMessage(message);
        } else {
            return null;
        }
    }

    public Message deleteMessageById(int id) {
        if(messageDAO.getMessageById(id) != null) {
            return messageDAO.deleteMessageById(id);
        } else {
            return null;
        }
    }

    public Message patchMessage(int id, Message newMessage) {
        return messageDAO.patchMessage(id, newMessage);
    }

    public List<Message> getMessagesByAccountId(int id) {
        return messageDAO.getMessagesByAccount(id);
    }

}
