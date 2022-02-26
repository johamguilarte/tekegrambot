package com.move.telegrambot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.move.telegrambot.bussines.UserTelegramBusiness;
import com.move.telegrambot.bussines.UsuariosBusiness;
import com.move.telegrambot.model.dto.UserIdDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import com.move.telegrambot.model.frontend.UsuarioOutput;
import com.move.telegrambot.service.sunacrip.SunacripService;
import com.move.telegrambot.service.usuarios.UsuariosService;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class BotControllers implements TelegramMvcController {

        Logger log = LoggerFactory.getLogger(BotControllers.class);

        @Autowired
        SunacripService sunacripService;

        @Autowired
        UsuariosService uService;

        @Autowired
        UserTelegramBusiness userTelegramBusiness;

        @Autowired
        UsuariosBusiness usuariosBusiness;

        private String token = "5112896084:AAEc3Bq1oMSOI7bhomU7BMoinUO1qQK1340";

        ObjectMapper mapper = new ObjectMapper();

        @Override
        public String getToken() {
                return token;
        }

        @BotRequest(value = "/crear", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest crear_error(User user, Chat chat) throws JsonProcessingException {
                UserIdDto usuario = this.userTelegramBusiness.findByUser(user.id().toString());
                if (usuario == null) {
                        UserIdDto commnad = this.userTelegramBusiness.guardar(user.id().toString(),
                                        chat.firstName() + " " + chat.lastName());
                        this.usuariosBusiness.saveUser(commnad.getIdTelegram());
                        return new SendMessage(chat.id(),
                                        "Registro exitoso, Bienvenido " + chat.firstName() + " " + chat.lastName());
                } else {
                        return new SendMessage(chat.id(),
                                        "Lo siento " + chat.firstName() + " " + chat.lastName()
                                                        + " ya estas registrado.");
                }
        }

        @BotRequest(value = "/usuariosbot", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest consulta(User user, Chat chat) throws JsonProcessingException {
                UsuarioOutput salida = uService.operationPri("/usuariosbot", user.id().toString());
                if (salida.getObjeto() == null)
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getRespuesta());
                else
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getObjeto());
        }

        @BotRequest(value = "/root {name:[\\S]+} {rol:[\\S]+}", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest crear(User user, Chat chat, @BotPathVariable("name") String userName,
                        @BotPathVariable("rol") String rol) throws JsonProcessingException {
                UserIdDto usuario = this.userTelegramBusiness.findByUser(user.id().toString());
                if (usuario != null) {

                        if (rol.equals("1000") || rol.equals("1200") || rol.equals("1300")) {
                                UsuariosDto users = this.usuariosBusiness.findByUser(userName.toUpperCase());
                                if (users != null && users.getRol().equals("1000")) {
                                        this.userTelegramBusiness.cambiarRol(chat.id().toString(), rol);
                                        this.usuariosBusiness.cambiarRol(chat.id().toString(), rol);
                                        return new SendMessage(chat.id(),
                                                        "Hola " + chat.firstName() + " " + chat.lastName() + "\n" + "\n"
                                                                        + "Rol actualizado! ,");

                                } else {
                                        return new SendMessage(chat.id(),
                                                        "Hola " + chat.firstName() + " " + chat.lastName() + "\n" + "\n"
                                                                        + "usuario root invalido, vuelve a intentarlo!.");
                                }
                        } else {
                                return new SendMessage(chat.id(), "Lo sentimos pero, " + "\n" + "El rol " + rol
                                                + " no existe, /help te dara los roles disponibles y comandos habilitados para ti");
                        }
                } else {
                        return new SendMessage(chat.id(), "Lo sentimos " + chat.firstName() + " " + chat.lastName() + ""
                                        + "\n"
                                        + "No estas registrado en el bot para su uso, para registrarlo usa el comando /crear");
                }
        }

        @BotRequest(value = "/aperturas", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest aperturas(User user, Chat chat) throws JsonProcessingException {
                UsuarioOutput salida = sunacripService.operationPri(user.id().toString(), "/aperturas");
                if (salida.getObjeto() == null)
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getRespuesta());
                else
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getObjeto());
        }

        @BotRequest(value = "/billetera", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest billetera(User user, Chat chat) throws JsonProcessingException {
                UsuarioOutput salida = sunacripService.operationPri(user.id().toString(), "/billeteraAll");
                if (salida.getObjeto() == null)
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getRespuesta());
                else
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getObjeto());
        }

        @BotRequest(value = "/tasa", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest tasa(User user, Chat chat) throws JsonProcessingException {
                UsuarioOutput salida = sunacripService.operationPri(user.id().toString(), "/tasa");
                if (salida.getObjeto() == null)
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getRespuesta());
                else
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + salida.getObjeto());
        }

        @BotRequest(value = "/{name:[\\S]+}", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest random(User user, Chat chat, @BotPathVariable("name") String userNam)
                        throws JsonProcessingException {
                UserIdDto usuario = this.userTelegramBusiness.findByUser(user.id().toString());
                if (usuario != null) {
                        return new SendMessage(chat.id(),
                                        "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                        + "el comando " + userNam
                                                        + " no existe, para visualizar el resto de comandos disponibles para ti utiliza /help");
                } else {
                        return new SendMessage(chat.id(), "Lo sentimos " + chat.firstName() + " " + chat.lastName() + ""
                                        + "\n"
                                        + "No estas registrado en el bot para su uso, para registrarlo usa el comando /crear");
                }
        }

        @BotRequest(value = "/help", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest help(User user, Chat chat)
                        throws JsonProcessingException {
                UsuarioOutput salida = uService.operationPri("/help", user.id().toString());
                log.info(salida.getObjeto().toString());
                return new SendMessage(chat.id(),
                                "Hola! " + chat.firstName() + " " + chat.lastName() + "." + "\n" + "\n"
                                                + salida.getObjeto());

        }

        @BotRequest(value = "/start", type = { MessageType.CALLBACK_QUERY,
                        MessageType.MESSAGE })
        public BaseRequest start(User user, Chat chat) throws JsonProcessingException {
                return new SendMessage(chat.id(), "Bievenido al bot de las criptomonedas: "
                                + chat.firstName() + " " + chat.lastName() + "\n" + "\n"
                                + "Instrucciones" + "\n" + "\n"
                                + "- Para registrarte escribe /crear" + "\n" + "\n"
                                + "- Para mas informacion visita el enlace: https://www.petro.gob.ve/es/" +
                                "\n");

        }

}