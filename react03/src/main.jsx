import React, { StrictMode } from 'react'
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import YoutubeTitleFetcher from './components/YoutubeTitleFetcher.jsx';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<App/>}></Route>
        <Route path='/video' element={<YoutubeTitleFetcher />}></Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
