import {BASE_URL} from '@env'

const ISSUES_URL = BASE_URL + "issues"
const ISSUE_MODELS_URL = BASE_URL + "issues/models"
const SURVEYS_URL = BASE_URL + "surveys"
const FEEDBACKS_URL = BASE_URL + "feedbacks"

const getCommentUrl = (id: string) => `${ISSUES_URL}/${id}/comments`

export {ISSUES_URL, ISSUE_MODELS_URL, getCommentUrl, SURVEYS_URL, FEEDBACKS_URL}
