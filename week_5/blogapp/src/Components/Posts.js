import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            hasError: false
        };
    }

    componentDidMount() {
        const samplePosts = [
            { id: 1, title: 'First Blog Post', content: 'Welcome to my blog!', author: 'Admin' },
            { id: 2, title: 'React Lifecycle', content: 'Learning componentDidMount and componentDidCatch.', author: 'Admin' }
        ];
        this.setState({ posts: samplePosts });
    }

    componentDidCatch(error, info) {
        this.setState({ hasError: true });
        console.error('Error caught:', error, info);
    }

    render() {
        if (this.state.hasError) {
            return <h1>Something went wrong.</h1>;
        }
        return (
            <div className="posts">
                <h1>Blog Posts</h1>
                {this.state.posts.map(post => (
                    <Post key={post.id} title={post.title} content={post.content} author={post.author} />
                ))}
            </div>
        );
    }
}

export default Posts;
