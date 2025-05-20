import { useState } from 'react'
import Clock from './Clock'
import Login from './Login'
import { Route, Routes } from 'react-router-dom'
import { useNavigate, Navigate } from 'react-router-dom'
import './App.css'

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const [alarm, setAlarm] = useState([]);
  const [alarmPopup, setAlarmPopup] = useState(null);

  const handleLogin = () => {
    setIsLoggedIn(true);
    setIsLoginModalOpen(false); // 로그인 성공 시 모달을 닫음
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  const openLoginModal = () => {
    setIsLoginModalOpen(true);
  };

  const closeLoginModal = () => {
    setIsLoginModalOpen(false);
  };

  const addAlarm = (time, message) => {
    if(alarm.length < 5){
      setAlarm([...alarm, { time, message }]);
    } else{
      alert('You can only set up to 5 alarms.');
    }
  }

  const removeAlarm = (index) => {
    const newAlarm = alarm.filter((_, i) => i !== index);
    setAlarm(newAlarm);
  }

  const checkAlarms = () => {
    const currentTime = new Date().toLocaleDateString('en-GB', {
      hour: '2-digit',
      minute: '2-digit',
    });

    alarm.forEach((a, index) => {
      if(a.time === currentTime) {
        setAlarmPopup(a.message)
        removeAlarm(index);
      }
    })
  }


  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/clock" />} />
        <Route
          path="/clock"
          element={
          <Clock
           isLoggedIn={isLoggedIn}
           onLogout={handleLogout}
           onLoginClick={openLoginModal}
           alarm={alarm}
           addAlarm={addAlarm}
           checkAlarms={checkAlarms}
           alarmPopup={alarmPopup}
           closePopup={() => setAlarmPopup(null)}
           />}
        />
      </Routes>
      
      {isLoginModalOpen && <Login onLogin={handleLogin} onClose={closeLoginModal} />}
    </>
  )
}

export default App
