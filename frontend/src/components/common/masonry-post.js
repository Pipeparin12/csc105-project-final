import React from 'react'
import {Link} from 'react-router-dom'
// import {TagRow} from './'


export default function MasonryPost ({post, tagsOnTop}) {
    const windowWidth = window.innerWidth
    console.log(post.image)
    const imageBackground = {backgroundImage: `url("${post.image_url}")`}
    console.log(imageBackground)
    const style = windowWidth > 900 ? {...imageBackground, ...post.style} : imageBackground

    return (
        <Link className="masonry-post overlay" style={style} to={`/blog/${post.blog_id}`}>
            <div className="image-text" style={{justifyContent: tagsOnTop ? 'space-between' : 'flex-end'}}>
                {/* <TagRow tags={post.categories} /> */}
                <div>
                    <h2 className="image-title">{post.title}</h2>
                    {/* <span className="image-date">{post.date}</span> */}
                </div>
            </div>
        </Link>
    )
}