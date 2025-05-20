import React, {useState, useEffect} from "react";
import axios from 'axios';
import './Login.css'

export default function Login({ onLogin, onClose }) {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try{
            const response = await axios.post("http://localhost:8080/api/v1/login", { username, email });
            if(response.status === 200){
                console.log("Login Success");
                onLogin();
                onClose();
            }
        } catch(error){
            if(error.response){
                console.log(error.response)
                setError(error.response.data)
            }else if(error.request){
                setError("Conncetion failed : " , error.request);
            }else{
                setError("Error occur while processing login", error.message);
            }
        }
    };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Login</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="modal-buttons">
            <button type="submit" className="login-button">Login</button>
            <button type="button" className="close-button" onClick={onClose}>Close</button>
          </div>
        </form>
      </div>
    </div>
  )
}
