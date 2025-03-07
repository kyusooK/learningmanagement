
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import LectureLectureManager from "./components/listers/LectureLectureCards"
import LectureLectureDetail from "./components/listers/LectureLectureDetail"

import GetLetcureView from "./components/GetLetcureView"
import GetLetcureViewDetail from "./components/GetLetcureViewDetail"
import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import StudyStudyManager from "./components/listers/StudyStudyCards"
import StudyStudyDetail from "./components/listers/StudyStudyDetail"

import LecturesupportAssignmentManager from "./components/listers/LecturesupportAssignmentCards"
import LecturesupportAssignmentDetail from "./components/listers/LecturesupportAssignmentDetail"
import LecturesupportLectureSuggestionManager from "./components/listers/LecturesupportLectureSuggestionCards"
import LecturesupportLectureSuggestionDetail from "./components/listers/LecturesupportLectureSuggestionDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/lectures/lectures',
                name: 'LectureLectureManager',
                component: LectureLectureManager
            },
            {
                path: '/lectures/lectures/:id',
                name: 'LectureLectureDetail',
                component: LectureLectureDetail
            },

            {
                path: '/lectures/getLetcures',
                name: 'GetLetcureView',
                component: GetLetcureView
            },
            {
                path: '/lectures/getLetcures/:id',
                name: 'GetLetcureViewDetail',
                component: GetLetcureViewDetail
            },
            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/studies/studies',
                name: 'StudyStudyManager',
                component: StudyStudyManager
            },
            {
                path: '/studies/studies/:id',
                name: 'StudyStudyDetail',
                component: StudyStudyDetail
            },

            {
                path: '/lecturesupports/assignments',
                name: 'LecturesupportAssignmentManager',
                component: LecturesupportAssignmentManager
            },
            {
                path: '/lecturesupports/assignments/:id',
                name: 'LecturesupportAssignmentDetail',
                component: LecturesupportAssignmentDetail
            },
            {
                path: '/lecturesupports/lectureSuggestions',
                name: 'LecturesupportLectureSuggestionManager',
                component: LecturesupportLectureSuggestionManager
            },
            {
                path: '/lecturesupports/lectureSuggestions/:id',
                name: 'LecturesupportLectureSuggestionDetail',
                component: LecturesupportLectureSuggestionDetail
            },



    ]
})
