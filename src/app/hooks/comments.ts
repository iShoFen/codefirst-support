import {Comment} from "../model/issues/Comment";
import {getCommentUrl} from "../config/constants";
import {mapComment} from "../utils/mappers";


const createComment = async (issueId: string, content: string, author: string): Promise<Comment | undefined> => {
  const body = {
    content: content,
    author: author,
    created_at: new Date(),
  }

  const options: RequestInit = {
    body: JSON.stringify(body),
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
  }

  console.log(options)

  const url = getCommentUrl(issueId)
  console.debug('[POST] - ', url)
  const response = await fetch(url, options)
  const json = await response.json()
  if (response.status !== 201) {
    console.error('[POST] - Erreur: ', response.status)
    console.error(json)
    return
  }

  return mapComment(json)
}

export {createComment}
