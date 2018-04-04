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

        this.updatePostList = this.updatePostList.bind(this);

        this.state = {
            isPostListLoading: true,
            postList: [],
            curCategoryId: this.props.defaultCategoryId,
            curCategoryTitle: this.props.defaultCategoryTitle,
            curPage: 0,
            noMorePost: false,
        };
    }

    componentDidMount() {
        GetHomePagePosts(this.props.defaultCategoryId, this.state.curPage, this.updatePostList);
    }

    updatePostList(data) {
        if (data && data.posts && data.posts.length > 0){
            const newPostlist = this.state.postList.concat(data.posts);
            this.setState({
                isPostListLoading: false,
                postList: newPostlist,
            });
        } else {
            this.setState({
                isPostListLoading: false,
                noMorePost: true,
            });
        }
    }

    handleNavCellOnClick(categoryId, categoryTitle) {
        if (this.state.curCategoryId !== categoryId) {
            this.setState({ 
                isPostListLoading: true, 
                curCategoryId: categoryId,
                curCategoryTitle: categoryTitle,
                curPage: 0, 
                postList: [],
                noMorePost: false,
            });
            GetHomePagePosts(categoryId, 0, this.updatePostList);  
        }  
    }
    
    handleLoadMoreOnClick() {
        const newPage = this.state.curPage + 1;
        this.setState({
            isPostListLoading: true,
            curPage: newPage,
        });
        GetHomePagePosts(this.state.curCategoryId, newPage, this.updatePostList);
    }

    renderLoadMoreButton() {
        if (this.state.noMorePost) {
            return <a className='load-more disable' >{'到底了！'}</a>;
        } else {
            return <a className='load-more' onClick={this.handleLoadMoreOnClick.bind(this)}>{'点击加载更多'}</a>;
        }
    }

    render() {

        const loadMore = this.renderLoadMoreButton();
        const loading = <div className='loading-wrap'><Loading /></div>;
        return (
        	<div className='home-page'>
        		<Header />
                <Hero {...this.props.hero} />
        		<div className='home-page-body'>    			
                    <NavGrid navGrid={this.props.navGrid} onCellClick={this.handleNavCellOnClick.bind(this)} />
                    <PostListContainer posts={this.state.postList} category={this.state.curCategoryTitle} />
                    <div className={'loading-container'}>{ this.state.isPostListLoading ? loading : loadMore }</div>
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
