package common.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * This class is a wrapper for java's <code>Socket</code> that uses json strings
 * to transmit objects.
 * <p>
 * A <code>JsonSocket</code> holds a <code>Gson</code> object to convert
 * objects to json string and vice-versa, and a <code>Socket</code> to
 * send and receive json strings.
 * <p>
 * To create a <code>JsonSocket</code>, one can pass an existing socket
 * to the constructor of this class.
 * <p>
 * After a <code>JsonSocket</code> was successfully created, one can send and
 * receive objects with methods <code>send</code> and <code>get</code>.
 * <p>
 * Note that the other endpoint of the communication must use a
 * <code>JsonSocket</code> to send and receive objects correctly.
 * <p>
 * This class is a member of {@link }.
 *
 * @see com.google.gson.Gson
 * @see java.net.Socket
 *
 */
public class JsonSocket {

    public static final Charset ENCODING = Charset.forName("UTF-8");

    /**
     * The <code>Gson</code> object is used to convert between java and json.
     */
    private Gson mGson;
    /**
     * The underlying <code>Socket</code>.
     */
    private Socket mSocket;
    /**
     * Input stream of the socket.
     */
    private InputStream mIn;
    /**
     * Output stream of the socket.
     */
    private OutputStream mOut;

    /**
     * Initiates new socket with specified host and port and uses
     * that socket to transmit json strings.
     *
     * @param host    the host name.
     * @param port    the port number.
     * @throws java.io.IOException if an I/O error occurs when creating the socket or
     *         its input or output stream.
     * @see java.net.Socket#Socket(String, int)
     * @see #JsonSocket(java.net.Socket)
     */
    public JsonSocket(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    /**
     * Uses an existing socket to transmit json strings.
     *
     * @param socket    the socket object.
     * @throws java.io.IOException if an I/O error occurs when creating the
     *         input or output stream of the socket.
     */
    public JsonSocket(Socket socket) throws IOException {
        mGson = new Gson();
        mSocket = socket;
        mIn = mSocket.getInputStream();
        mOut = mSocket.getOutputStream();
    }

    /**
     * Closes the underlying socket.
     *
     * @throws java.io.IOException if an I/O error occurs when closing the socket.
     * @see java.net.Socket#close
     * @see #isClosed
     */
    public void close() throws IOException {
        mSocket.close();
    }

    /**
     * Returns the closed state of the socket.
     *
     * @return true if the socket has been closed
     * @see java.net.Socket#isClosed
     * @see #close
     */
    public boolean isClosed() {
        return mSocket.isClosed();
    }

    /**
     * Converts <code>obj</code> to json string and sends it via the socket's
     * output stream.
     *
     * @param obj    the object to send.
     * @throws java.io.IOException if an I/O error occurs, e.g. when the socket is closed.
     * @see com.google.gson.Gson#toJson(Object)
     */
    public void send(Object obj) throws IOException {
        String json = mGson.toJson(obj);
//        System.out.println("send : " + json);
        byte buffer[] = json.getBytes(ENCODING);
        mOut.write(buffer, 0, buffer.length);
        mOut.write('\0');
    }

    /**
     * Reads a json string from socket's input stream, and converts it to
     * a <code>JsonObject</code>.
     *
     * @return the received <code>JsonObject</code>
     * @throws java.io.IOException if an I/O error occurs, e.g. when the socket is closed.
     * @see com.google.gson.Gson#fromJson
     * @see #get
     */
    public JsonObject get() throws IOException {
        return get(JsonObject.class);
    }

    /**
     * Reads a json string from socket's input stream, and converts it to
     * a an object of the specified class.
     *
     * @param classOfInput    object's class
     * @param <T>             type
     * @return the received object
     * @throws java.io.IOException if an I/O error occurs, e.g. when the socket is closed.
     * @see com.google.gson.Gson#fromJson
     * @see #get
     */
    public <T> T get(Class<T> classOfInput) throws IOException {
        int length = 1000, total = 0, current;
        byte buffer[] = new byte[length];
        while (true) {
            current = mIn.read();
            if (current == -1)
                throw new IOException("EOF reached.");
            if (current == '\0')
                break;
            if (total >= length) {
                byte newBuffer[] = new byte[2*length];
                System.arraycopy(buffer, 0, newBuffer, 0, length);
                buffer = newBuffer;
                length *= 2;
            }
            buffer[total++] = (byte) current;
        }
        String json = new String(buffer, 0, total, ENCODING);
//        System.out.println("receive : " + json);
        return mGson.fromJson(json, classOfInput);
    }

}
