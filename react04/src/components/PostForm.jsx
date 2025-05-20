import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function PostForm() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const postData = { title, content };

    axios.post('http://localhost:8080/api/v1/posts', postData)
      .then(() => navigate('/'))
      .catch(error => console.error('Error creating post:', error));
  };

  return (
    <div>
      <h2>새 게시물 작성</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>제목</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div>
          <label>내용</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        </div>
        <button type="submit">게시물 작성</button>
      </form>
    </div>
  );
}

export default PostForm;