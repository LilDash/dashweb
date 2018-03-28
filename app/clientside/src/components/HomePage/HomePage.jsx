import * as React from 'react';
import PropTypes from 'prop-types';
import { GetHomePagePosts } from '../../services/PostService.js';
import { Header } from '../Header/Header';
import { Footer } from '../Footer/Footer';
import { Hero } from './subcomponents/Hero/Hero';
import { NavGrid } from './subcomponents/NavGrid/NavGrid';
import { PostListContainer } from './subcomponents/PostListContainer/PostListContainer';
import { PostList } from './subcomponents/PostList/PostList';
import { SectionBracket } from './subcomponents/SectionBracket/SectionBracket';
import { Loading } from '../common/Loading/Loading';
import 'reset-css/reset.css';
import '../../styles/default.scss';
import './home-page.scss';

export default class HomePage extends React.Component {

    constructor(props) {
        super(props);

        this.renderPostList = this.renderPostList.bind(this);

        this.state = {
            isPostListLoaded: false,
            postList: [],
            curCategory: this.props.defaultCategoryTitle,
        };
    }

    componentDidMount() {
        GetHomePagePosts(this.props.defaultCategoryId, this.renderPostList);
    }

    renderPostList(data) {
        this.setState({
            isPostListLoaded: true,
            postList: data,
        });
    }

    handleOnNavCellClick(categoryId, categoryTitle) {
        this.setState({ isPostListLoaded: false, curCategory: categoryTitle });
        GetHomePagePosts(categoryId, this.renderPostList);    
    }

    render() {
        return (
        	<div className='home-page'>
        		<Header />
                <Hero {...this.props.hero} />
        		<div className='home-page-body'>    			
                    <NavGrid navGrid={this.props.navGrid} onCellClick={this.handleOnNavCellClick.bind(this)} />
                    { !this.state.isPostListLoaded && <div className='loading-wrap'><Loading /></div> }
                    { this.state.isPostListLoaded && <PostListContainer {...this.state.postList} category={this.state.curCategory} /> }
        		</div>
        		<Footer />
        	</div>
        );
    }
};

HomePage.propTypes = {
    hero: PropTypes.object.isRequired,
    navGrid: PropTypes.array.isRequired,
    defaultCategoryId: PropTypes.number.isRequired,
    defaultCategoryTitle: PropTypes.string.isRequired,
};
