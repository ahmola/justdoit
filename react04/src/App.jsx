import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import PostList from './components/PostList';
import PostForm from './components/PostForm';

function App() {
  return (
    <Router>
      <div style={{ textAlign: 'center' }}>
        <h1>블로그 시스템</h1>
        <nav>
          <Link to="/">게시물 목록</Link> | <Link to="/new">새 게시물 작성</Link>
        </nav>
        <Routes>
          <Route path="/" element={<PostList />} />
          <Route path="/new" element={<PostForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;