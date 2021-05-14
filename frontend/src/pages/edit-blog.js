import React, {useState, useEffect} from 'react'
import {Link, useParams} from 'react-router-dom'
import '../assets/scss/_blog.scss'
import axios from '../axios'
import FormData from 'form-data'

export default function EditBlog () {
  const {id} = useParams()
  console.log(id)

  const [title, setTitle] = useState("")
  const [image_url, setImage_url] = useState("")
  const [content, setContent] = useState("")

  useEffect(() => {
    axios.get(`GetBlogInformationServlet?blog_id=${id}`).then((res) => {
      setTitle(res.data.blog.title)
      setImage_url(res.data.blog.image_url)
      setContent(res.data.blog.content)
    })
  },[])

  const submit = (() => {
    const formData = new FormData()
    formData.append("blog_id", id)
    formData.append("title", title)
    formData.append("image_url", image_url)
    formData.append("content", content)
    axios.put("GetBlogInformationServlet", formData).then((res) => {
      window.location.href="/blog"
    })
  })

  const deleteBlog = (() => {
    const formData = new FormData()
    formData.append("blog_id", id)
    axios.delete("GetBlogInformationServlet", formData).then((res) => {
      window.location.href="/home"
    })
  })

    return (
      <main className="blog-container">
        <section className="blog-content">

            <div className="title">
              <textarea className="edit-area" id="" cols="50" rows="1" value={title} onChange={((e) => {setTitle(e.target.value)})}/>
            </div>

              <img className="img" src={image_url} alt="" />

              <div className="input-img">
              <textarea className="edit-area" id="" cols="60" rows="1"value={image_url} onChange={((e) => {setImage_url(e.target.value)})}/>
              </div>

              <div className="blog-text">
                <textarea className="edit-area" id="" cols="200" rows="10" value={content} onChange={((e) => {setContent(e.target.value)})}/>
              </div>

              <div className="delete-info">
                  <button onClick={deleteBlog}>Delete Blog</button> 
              </div>

              <div className="edit-info">
                  <button onClick={submit}>Submit</button> 
              </div>
          </section> 
      </main>   
    )
}