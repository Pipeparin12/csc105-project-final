import React, { useState } from 'react'
import {Link} from 'react-router-dom'
import '../assets/scss/_blog.scss'
import FormData from 'form-data'
import axios from '../axios'

export default function CreateBlog() {
  const CreateBlog = async () => {
    console.log(title)
    console.log(image_url)
    console.log(content)
    const formData = new FormData()         
    formData.append("title", title)         
    formData.append("image_url", image_url)
    formData.append("content", content)
    const res = await axios.post("CreateBlogServlet", formData)
    console.log(res)
  }

  const [title, setTitle] = useState("")
  const [image_url, setImage_url] = useState("")
  const [content, setContent] = useState("")

    return(
        <main className="blog-container">
        <section className="blog-content">

            <div className="title">
              <p className="topic">Title</p>
              <textarea className="edit-area" id="" cols="50" rows="1" onChange={(e) => setTitle(e.target.value)}></textarea>
            </div>
            
              <div className="input-img">
                <p className="topic">img URL</p>
              <textarea className="edit-area" id="" cols="60" rows="1" onChange={(e) => setImage_url(e.target.value)}></textarea>
              </div>

              <div className="blog-text">
                <p className="topic">Content</p>
                <textarea className="edit-area" id="" cols="200" rows="10" onChange={(e) => setContent(e.target.value)}></textarea>
              </div>

              <div className="delete-info">
                <Link to="/home">
                  <button>Delete Blog</button> 
                </Link>
              </div>

              <div className="edit-info">
                <Link to="/blog">
                  <button onClick={CreateBlog}>Submit</button> 
                </Link>
              </div>
          </section> 
      </main>   
    )
}