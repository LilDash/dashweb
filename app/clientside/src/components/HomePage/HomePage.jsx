import * as React from 'react';
import PropTypes from 'prop-types';
import { GetHomePagePosts } from '../../services/PostService.js';
import { Header } from '../Header/Header';
import { Footer } from '../Footer/Footer';
import { Hero } from './subcomponents/Hero/Hero';
import { NavGrid } from './subcomponents/NavGrid/NavGrid';
import { PostList } from './subcomponents/PostList/PostList';
import { SectionBracket } from './subcomponents/SectionBracket/SectionBracket';
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
        };
    }

    componentDidMount() {
        GetHomePagePosts(this.renderPostList);
    }

    renderPostList(data) {
        this.setState({
            isPostListLoaded: true,
            postList: data,
        });  
    }

    render() {
        return (
        	<div className='home-page'>
        		<Header />
                <Hero {...this.props.hero} />
        		<div className='home-page-body'>    			
                    <NavGrid navGrid={this.props.navGrid} />
                    <SectionBracket name='最新发布' direction='down' />
                    { this.state.isPostListLoaded && <PostList {...this.state.postList} /> }
        		</div>
        		<Footer />
        	</div>
        );
     }
};

HomePage.propTypes = {
    hero: PropTypes.object.isRequired,
    navGrid: PropTypes.array.isRequired,
};
