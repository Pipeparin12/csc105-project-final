import React, {useState, useEffect} from 'react'
import {Link, useParams} from 'react-router-dom'
import '../assets/scss/_blog.scss'
import axios from '../axios'

export default function Blog () {
  const {id} = useParams()
  console.log(id)

  const [blog, setBlog] = useState({})
  const [Owner, setOwner] = useState(false)

  useEffect(() => {
    axios.get(`GetBlogInformationServlet?blog_id=${id}`).then((res) => {
      setBlog(res.data.blog)
      setOwner(res.data.Owner)
    })
  },[])
  
    return (
      <main className="blog-container">
        <section className="blog-content">

            <div className="title">
              <h2 className="text-title">{blog.title}</h2>
            </div>

              <img className="img" src={blog.image_url} alt="" />

              <div className="blog-text">
              <p>{blog.content}</p>
              </div>

              {Owner?(<div className="edit-info">
                <Link to = {`/edit-blog/${id}`} >
                  <button>Edit Blog</button>
                </Link>
              </div>):<div></div>}

          </section> 
      </main>   
    )
}