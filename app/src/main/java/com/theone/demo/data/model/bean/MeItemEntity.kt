package com.theone.demo.data.model.bean

import com.theone.demo.data.model.enums.MeItemType

/**
 * @author : hgj
 * @date   : 2020/6/11
 *
 */
data class MeItemEntity(
    var itemType: MeItemType,
    var itemPosition: Int,
    var data: Any
)