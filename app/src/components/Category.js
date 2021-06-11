import React, { Component } from 'react'

class Category extends Component {
    state = { 
        isLoading : true,
        categories: []
    }

    async componentDidMount() {
        const response = await fetch('/api/v1/categories');
        const body = await response.json();
        this.setState({categories: body, isLoading: false})
    }
    
    render() { 
        const {categories, isLoading} = this.state

        if (isLoading) {
            return (<div>Loading...</div>)
        }

        return (
            <div>
                <h2>Categories</h2>
                {
                    categories.map(category =>
                        <div id={category.id}>
                            {category.label}
                        </div>
                    )
                }
            </div>
        );
    }
}
 

export default Category;