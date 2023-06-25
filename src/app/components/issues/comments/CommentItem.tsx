import {StyleSheet, TouchableOpacity, View, ViewStyle} from "react-native";
import {Comment} from "../../../model/issues/Comment";
import React, {useCallback} from "react";
import {useColors} from "../../../themes/hooks/useColors";
import CSDivider from "../../commons/CSDivider";
import CSText from "../../commons/CSText";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {Swipeable} from "react-native-gesture-handler";

type CommentItemProps = {
  comment: Comment
  style?: ViewStyle
  onDelete: (comment: Comment) => void
}

export default function CommentItem(props: CommentItemProps) {

  const colors = useColors()

  const {
    comment,
    style,
    onDelete
  } = props

  const handleDelete = useCallback(() => {
    onDelete(comment)
  }, [])

  return (
    <Swipeable
      renderRightActions={() => (
        <TouchableOpacity
          onPress={handleDelete}
          style={{
            backgroundColor: colors.danger,
            margin: 4,
            paddingHorizontal: 20,
            borderRadius: 8,
            justifyContent: 'center'
          }}>
            <MaterialCommunityIcons name="delete" color={colors.white} size={24}/>
        </TouchableOpacity>
      )}>
      <View style={[styles.container, style, {
        backgroundColor: colors.backgroundVariant
      }]}>
        <View style={styles.header}>
          <View style={styles.headerText}>
            <CSText text={comment.author} type="bold"/>
            <CSText text="-" type="small"/>
            <CSText text={comment.createdAt.toLocaleDateString()} type="small"/>
          </View>
          {/*<TouchableOpacity style={{*/}
          {/*  alignSelf: 'flex-end'*/}
          {/*}}>*/}
          {/*  <MaterialCommunityIcons name="dots-horizontal" size={24} color={colors.text}/>*/}
          {/*</TouchableOpacity>*/}
        </View>
        <CSDivider/>
        <CSText text={comment.content} type="small"/>
      </View>
    </Swipeable>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 4,
    paddingVertical: 8,
    paddingHorizontal: 16,
    backgroundColor: 'white',
    borderRadius: 8
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  headerText: {
    flexDirection: 'row',
    alignItems: 'baseline',
    gap: 4
  }
})
